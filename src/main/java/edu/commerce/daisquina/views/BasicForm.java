package edu.commerce.daisquina.views;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import edu.commerce.daisquina.entity.Product;
import edu.commerce.daisquina.repository.ProductRepo;

@Route("")
@PageTitle("Formulário de Cadastro")
public class BasicForm extends VerticalLayout {

    public BasicForm(ProductRepo repository) {
        var grid = new Grid<Product>();

        grid.addColumn(Product::getID)
                        .setHeader("Código");
        grid.addColumn(Product::getName)
                .setHeader("Nome");
        grid.addColumn(Product::getDescription)
                .setHeader("Descrição");
        grid.addColumn(Product::getPrice)
                .setHeader("Preço")
                .setTextAlign(ColumnTextAlign.END);

        grid.setItemsPageable(pageable ->
            repository
                    .findAll(pageable)
                    .getContent()
        );

        setSizeFull();
        grid.setSizeFull();
        add(grid);

    }
}
