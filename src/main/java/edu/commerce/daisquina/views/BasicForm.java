package edu.commerce.daisquina.views;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import edu.commerce.daisquina.entity.Product;
import edu.commerce.daisquina.repository.ProductDetailsRepository;
import edu.commerce.daisquina.repository.ProductRepo;
import edu.commerce.daisquina.views.drawer.ProductFormDrawer;

@Route("")
@PageTitle("Formulário de Cadastro de Produtos")
public class BasicForm extends HorizontalLayout {

    public BasicForm(ProductRepo repository, ProductDetailsRepository detailsRepository) {
        var searchField = new TextField();
        searchField.setPlaceholder("Procurar");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setValueChangeMode(ValueChangeMode.LAZY);

        var grid = new Grid<Product>();

        searchField.addValueChangeListener(
                e -> grid.getDataProvider().refreshAll());

        grid.addColumn(Product::getID)
                .setHeader("Código");
    grid.addColumn(Product::getName)
                .setHeader("Nome")
                .setSortProperty("Nome");
        grid.addColumn(Product::getDescription)
                .setHeader("Descrição")
                .setSortProperty("Descricao");
        grid.addColumn(Product::getPrice)
                .setHeader("Preço")
                .setTextAlign(ColumnTextAlign.END)
                .setSortProperty("Preço");

        var drawer = new ProductFormDrawer();

        grid.setItemsPageable(pageable ->
            repository
                    .findByNameContainingIgnoreCase(searchField.getValue(), pageable)
                    .getContent()
        );

        grid.addSelectionListener(e -> {

            var productDetails = e.getFirstSelectedItem()
                    .flatMap(item -> detailsRepository.findById(item.getID()))
                    .orElse(null);
            drawer.setProductDetails(productDetails);

        });

        setSizeFull();
        setSpacing(false);

        var listLayout = new VerticalLayout(searchField, grid);
        listLayout.setSizeFull();

        grid.setSizeFull();
        add(listLayout, drawer);
        setFlexShrink(0, drawer);

    }
}
