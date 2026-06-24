package edu.commerce.daisquina.views.form;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import edu.commerce.daisquina.entity.Product;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class ProductForm extends Composite<FormLayout> {

    private final Binder<Product> binder;

    public ProductForm(){

        var nameField = new TextField("Nome do produto");
        var descriptionField = new TextArea("Descrição do produto");
        var priceField = new BigDecimalField("Preço do produto");

        var layout = getContent();
        layout.add(nameField);
        layout.add(descriptionField);
        layout.add(priceField);

        binder = new Binder<>();
        binder.forField(nameField)
                .asRequired("Digite o nome do produto")
                .bind(Product::getName,
                        Product::setName);
        binder.forField(descriptionField)
                .asRequired("Digite uma descrição para o produto")
                .bind(Product::getDescription,
                        Product::setDescription);
        binder.forField(priceField)
                .asRequired("Digite o preço do produto")
                .bind(Product::getPrice,
                        Product::setPrice);


    }

    public void setFormDataObject(@Nullable Product product){

        binder.setBean(product);

    }

    public Optional<Product> getProduct(){
        if(binder.getBean() == null){ throw new IllegalStateException("Cannot get product object"); }

        if(binder.validate().isOk()){
            return Optional.of(binder.getBean());
        }else {
            return Optional.empty();
        }

    }

}
