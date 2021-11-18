package academy.digitallab.shoppingservice.service;

import academy.digitallab.shoppingservice.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> getAll();
    Invoice create(Invoice invoice);
    Invoice update(Invoice invoice);
    Invoice delete(Long id);
    Invoice getById(Long id);
}
