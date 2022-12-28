package polyclinic.srs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import polyclinic.srs.entities.*;
import polyclinic.srs.repositories.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return (Users) authentication.getPrincipal();
        }
        return null;
    }

    @GetMapping("/")
    public String indexPage(Model model){
        model.addAttribute("currentUser", getUser());
        return "halkbank";
    }

    @GetMapping("/about")
    public String AboutPage(Model model){
        model.addAttribute("currentUser", getUser());
        return "aksia";
    }

    @GetMapping("/items")
    public String blogPage(Model model){
        model.addAttribute("currentUser", getUser());
        return "items";
    }

    @GetMapping("/contact")
    public String ContactPage(Model model){
        model.addAttribute("currentUser", getUser());
        return "contact";
    }

    @GetMapping("/kredits")
    public String kreditPage(Model model) {
        model.addAttribute("currentUser", getUser());
        return "kredits";
    }


    @GetMapping("/transfer")
    @PreAuthorize("isAuthenticated()")
    public String transferPage(Model model){
        model.addAttribute("currentUser", getUser());
        return "transfer";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/payments")
    public String paymentsPage(Model model){
        model.addAttribute("currentUser", getUser());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("payments", paymentRepository.findAll());
        return "payments";
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping("/items/{itemId}")
    public String blogsGet(Model model,
                           @PathVariable(name = "itemId") Long id) {
        Items items = itemRepository.getById(id);
        List<Comments> comments = commentsRepository.findAllByItemsId(id);
        model.addAttribute("currentUser", getUser());
        model.addAttribute("items", items);
        model.addAttribute("comments", comments);
        return "single-item";
    }

    @GetMapping("/payments/done")
    @PreAuthorize("isAuthenticated")
    public String moneyTransfer(@RequestParam("toAccount")String account, @RequestParam("amount")int amount){
        Users user = userRepository.findByAccount(account);
        if(user == null || getUser().getMoney() > amount) return "not_success";
        getUser().setMoney(getUser().getMoney() - amount);
        user.setMoney(user.getMoney()+amount);
        return "success";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addcomment")
    public String addComment(Model model,
                             @RequestParam(name = "itemid")Long itemid,
                             @RequestParam(name = "userid")Long userid,
                             @RequestParam(name = "comment")String comment){
        Optional<Users> opt = userRepository.findById(userid);
        Optional<Items> items = itemRepository.findById(itemid);
        model.addAttribute("currentUser", getUser());
        Comments comments = new Comments();
        comments.setComment(comment);
        Users users = opt.get();
        Items item = items.get();
        comments.setUsers(users);
        comments.setItems(item);
        commentsRepository.save(comments);
        return "redirect:/items/"+itemid;
    }
}
