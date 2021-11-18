package academy.digitallab.shoppingservice.service;

import academy.digitallab.shoppingservice.entity.Invoice;
import academy.digitallab.shoppingservice.repository.InvoiceItemsRepository;
import academy.digitallab.shoppingservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemsRepository invoiceItemsRepository;
    //@Autowired
    //CustomerClient customerClient;

    //@Autowired
    //ProductClient productClient;

    @Override
    public List<Invoice> getAll() {
        return  invoiceRepository.findAll();
    }

    @Override
    public Invoice create(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDB != null){
            return  invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        /*
        invoiceDB.getItems().forEach( invoiceItem -> {
            productClient.updateStockProduct( invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
        });
         */

        return invoiceDB;
    }

    @Override
    public Invoice update(Invoice invoice) {
        Invoice invoiceDB = getById(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }

        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice delete(Long id) {
        Invoice invoiceDB = getById(id);
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getById(Long id) {

        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        //if (null != invoice ){
            /*
            Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);
            List<InvoiceItem> listItem=invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listItem);
             */
        //}
        return invoice ;
    }
}
