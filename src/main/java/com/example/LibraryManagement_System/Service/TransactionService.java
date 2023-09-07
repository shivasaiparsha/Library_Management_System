package com.example.LibraryManagement_System.Service;

import com.example.LibraryManagement_System.Book;
import com.example.LibraryManagement_System.CustomeExceptions.BookNotFoundException;
import com.example.LibraryManagement_System.CustomeExceptions.InvalidCardId;
import com.example.LibraryManagement_System.Enum.TransactionStatuses;
import com.example.LibraryManagement_System.Enum.TransactionType;
import com.example.LibraryManagement_System.Library_Card;
import com.example.LibraryManagement_System.Repositary.BookRepositary;
import com.example.LibraryManagement_System.Repositary.LibraryCardRepo;
import com.example.LibraryManagement_System.Repositary.TransactionRepositary;
import com.example.LibraryManagement_System.RequestDto.ReturnBookDto;
import com.example.LibraryManagement_System.RequestDto.issueBookReqDTO;
import com.example.LibraryManagement_System.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepositary transactionRepositary;

    @Value("${book.maxLimit}")
    private Integer maxBookLimit;
    @Autowired
    private BookRepositary bookRepositary;

    @Autowired
    private LibraryCardRepo libraryCardRepo;

    public String issuebooktostudent(issueBookReqDTO issueBookReqDTO) throws Exception
    {
        Integer bookId= issueBookReqDTO.getBookId();
        Integer cardId= issueBookReqDTO.getCardId();
        Transaction transaction = new Transaction( TransactionType.ISSUE,TransactionStatuses.PENDING,0);
        Optional<Book> optionalBook = bookRepositary.findById(bookId);
           if(!optionalBook.isPresent()){
               throw new BookNotFoundException("Incorrect book id entered");
           }
           Book book =optionalBook.get();
           if(book.isAvailable()==false)
           {
               throw new BookNotFoundException("Book not Availble");
           }



        Optional<Library_Card> optionalLibraryCard=libraryCardRepo.findById(cardId);
           if(optionalLibraryCard.isPresent()==false)
               throw new InvalidCardId("entered cardId is invalid");
           Library_Card libraryCard=optionalLibraryCard.get();

           if(!libraryCard.getCardStatus().equals("ACTIVE"))
           {
               transaction.setTransactionStatuses(TransactionStatuses.FAILURE);
              transaction=transactionRepositary.save(transaction);
              throw new Exception("Card Status is Not Active");
           }


         if(libraryCard.getNoOfBookIssued()>=maxBookLimit)
         {
             throw new Exception("Book Limit Exceeded");
         }

          transaction.setTransactionStatuses(TransactionStatuses.SUCCESS);
         book.setAvailable(false);

         libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued()+1);
         transaction.setBook(book);
         transaction.setLibraryCard(libraryCard);
         Transaction newTransaction=transactionRepositary.save(transaction);

         book.getTransactionList().add(newTransaction);
         libraryCard.getTransactionList().add(newTransaction);

         bookRepositary.save(book);
         libraryCardRepo.save(libraryCard);
        return "book issued successfully7";
    }

    public String returnBook(ReturnBookDto returnBookDto)
    {
        int cardId=returnBookDto.getCardId();
        int bookId=returnBookDto.getBookId();

        Book book=bookRepositary.findById(bookId).get();
        Library_Card card =libraryCardRepo.findById(cardId).get();

        List<Transaction> transactionList = transactionRepositary.findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(book,card,TransactionStatuses.SUCCESS, TransactionType.ISSUE);

            Transaction latestTransaction=transactionList.get(transactionList.size()-1);

            Date issuDate=latestTransaction.getCreattimeat();

            long milliSecondsTime=Math.abs(System.currentTimeMillis()-issuDate.getTime());
            long noOfDaysIssued= TimeUnit.DAYS.convert(milliSecondsTime, TimeUnit.MILLISECONDS);

            int fineAmount=0;
            if(noOfDaysIssued>15)
            {
                fineAmount=(int) ((noOfDaysIssued-15)*5);
            }

            book.setAvailable(true);
            card.setNoOfBookIssued(card.getNoOfBookIssued()-1);
            Transaction transaction = new Transaction(TransactionType.RETURN, TransactionStatuses.SUCCESS,fineAmount);
            transaction.setBook(book);
            transaction.setLibraryCard(card);
            Transaction newTransactiom=transactionRepositary.save(transaction);
             book.getTransactionList().add(transaction);
             card.getTransactionList().add(transaction);
             bookRepositary.save(book);
             libraryCardRepo.save(card);
             return "Book successfully returned";
    }
}

