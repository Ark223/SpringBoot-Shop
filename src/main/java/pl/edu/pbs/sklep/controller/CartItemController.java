package pl.edu.pbs.sklep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pbs.sklep.ShopApplication;
import pl.edu.pbs.sklep.model.CartItem;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.model.User;
import pl.edu.pbs.sklep.repository.CartItemRepository;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class CartItemController {
    @Autowired
    CartItemRepository cartItemRepository;

    @PostMapping("/add")
    public void addItemToCart(Product product, User user) {
        Integer userId = user.getId();
        if (!ShopApplication.loggedIn.equals(userId)) return;
        CartItem item = new CartItem(0, product, user);
        cartItemRepository.save(item);
    }

    @GetMapping("/")
    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findAll().stream()
            .filter(i -> i.getUserId().equals(user.getId()))
            .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{cartItemId}")
    public void deleteItemFromCart(Product product, User user) {
        Integer userId = user.getId();
        CartItem item = cartItemRepository.findAll().stream()
            .filter(i -> i.getProductId().equals(product.getId()) &&
            i.getId().equals(user.getId())).findFirst().orElse(null);
        if (item != null) cartItemRepository.delete(item);
    }
}
