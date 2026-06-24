package edu.commerce.daisquina.views.drawer;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import edu.commerce.daisquina.entity.Product;
import edu.commerce.daisquina.views.form.ProductForm;
import org.jspecify.annotations.Nullable;

public class ProductFormDrawer extends Composite<VerticalLayout> {

    @FunctionalInterface
    public interface SaveCallback {
        Product save(Product product);
    }
    @FunctionalInterface
    public interface ErrorCallBack{
        void handleException(RuntimeException ex);
    }

    private final ProductForm productForm;
    private final SaveCallback saveCallback;
    private final ErrorCallBack errorCallBack;

    public ProductFormDrawer(SaveCallback saveCallback,
                             ErrorCallBack errorCallBack) {

        this.saveCallback = saveCallback;
        this.errorCallBack = errorCallBack;

        var header = new H2("Detalhes do produto");
        productForm = new ProductForm();

        var saveButton = new Button("Salvar", e -> save());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        var layout = getContent();
        layout.add(header);
        layout.add(new Scroller(productForm));
        layout.add(saveButton);
        layout.setWidth("300px");
        addClassName(LumoUtility.BoxShadow.MEDIUM);
        setVisible(false);

    }

    public void setProductDetails(@Nullable Product product){

        productForm.setFormDataObject(product);
        setVisible(true);

    }

    private void save(){

        productForm.getProduct().ifPresent(product -> {
            try {
                var savedProduct = saveCallback.save(product);
                productForm.setFormDataObject(savedProduct);

            } catch (RuntimeException e) {
                errorCallBack.handleException(e);
            }

            });


    }

}
