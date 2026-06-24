package edu.commerce.daisquina.views.drawer;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import edu.commerce.daisquina.FDOs.ProductDetails;
import edu.commerce.daisquina.views.form.ProductForm;
import org.jspecify.annotations.Nullable;

public class ProductFormDrawer extends Composite<VerticalLayout> {

    private final ProductForm productForm;

    public ProductFormDrawer(){
        var header = new H2("Detalhes do produto");
        productForm = new ProductForm();

        var layout = getContent();
        layout.add(header);
        layout.add(new Scroller(productForm));
        layout.setWidth("300px");
        addClassName(LumoUtility.BoxShadow.MEDIUM);

    }

    public void setProductDetails(@Nullable ProductDetails productDetails){

        productForm.setFormDataObject(productDetails);

    }

}
