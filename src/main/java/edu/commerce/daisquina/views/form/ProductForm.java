package edu.commerce.daisquina.views.form;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import edu.commerce.daisquina.FDOs.ProductDetails;
import org.jspecify.annotations.Nullable;

public class ProductForm extends Composite<FormLayout> {

    private final Binder<ProductDetails> binder;

    public ProductForm(){

        var idField = new NumberField("Código do produto");
        var nameField = new TextField("Nome do produto");
        var descriptionField = new TextArea("Descrição do produto");
        var priceField = new BigDecimalField("Preço do produto");

        var layout = getContent();
        layout.add(nameField);
        layout.add(descriptionField);
        layout.add(priceField);

        binder = new Binder<>();
        binder.forField(nameField)
                .bind(ProductDetails::getName,
                        ProductDetails::setName);
        binder.forField(descriptionField)
                .bind(ProductDetails::getDescription,
                        ProductDetails::setDescription);
        binder.forField(priceField)
                .bind(ProductDetails::getPrice,
                        ProductDetails::setPrice);

        binder.setReadOnly(true);

    }

    public void setFormDataObject(@Nullable ProductDetails productDetails){

        binder.setBean(productDetails);

    }

}
