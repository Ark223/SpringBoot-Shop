package pl.edu.pbs.sklep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pbs.sklep.model.CartItem;
import pl.edu.pbs.sklep.model.Product;
import pl.edu.pbs.sklep.model.User;
import pl.edu.pbs.sklep.repository.CartItemRepository;
import pl.edu.pbs.sklep.repository.ProductRepository;
import pl.edu.pbs.sklep.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartItemController {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    @PostMapping("/add")
    public void addItemToCart(@RequestParam Integer productid, @RequestParam Integer userid) {
        Optional<User> user = userRepository.findById(userid);
        Optional<Product> product = productRepository.findById(productid);
        if (!user.isPresent() || !product.isPresent()) return;
        CartItem item = new CartItem(0, product.get(), user.get());
        cartItemRepository.save(item);
    }

    @PostMapping("/delete")
    public void deleteItemFromCart(@RequestParam Integer productid, @RequestParam Integer userid) {
        Optional<User> user = userRepository.findById(userid);
        Optional<Product> product = productRepository.findById(productid);
        if (!user.isPresent() || !product.isPresent()) return;
        CartItem item = cartItemRepository.findAll().stream()
            .filter(i -> i.getProductId().equals(product.get().getId()) &&
            i.getId().equals(user.get().getId())).findFirst().orElse(null);
        if (item != null) cartItemRepository.delete(item);
    }
}
