package com.eattingapp;

import com.eattingapp.dishes.Dishes;
import com.eattingapp.personaldata.PersonalAdress;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.Collection;

import static com.eattingapp.dishes.DishesSQLController.*;
import static com.eattingapp.personaldata.PersonalAdressSQLController.*;

@Controller
public class WebController {

    @RequestMapping(value = "/")
    public static String homePage(Model model) throws SQLException{
        model.addAttribute("dishes",  getDishes());
        return "page";
    }
    @RequestMapping(value = "/user")
    public static String userPage() throws SQLException{
        return "user";
    }

    @GetMapping(value = "/contact")
    public static String contactPage(Model model) throws SQLException {

        return "contact";
    }

    @RequestMapping(value = "/error404")
    public static String notFound() throws SQLException{
        return "error404";
    }

   /* // DEFAULT ERROR MAPPING
    @RequestMapping(value = "/error")
    public static String defaultError() throws SQLException{
        return "error404";
    }*/

    @RequestMapping(value = "/login")
    public static String loginPage() {
        return "login";
    }
    @GetMapping(value = "/admin")
    public static String adminPage(Model model) throws SQLException{
        model.addAttribute("id",0);
        model.addAttribute("dishes",new Dishes());
        model.addAttribute("dish1",new Dishes());
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("dishes1",  getDishes());
        return "admin";

    }
    @PostMapping(value = "dishes")
    public static String addDishes(@ModelAttribute Dishes dishes, Model model)throws SQLException{
        model.addAttribute("id",0);
        model.addAttribute("dish1",new Dishes());
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("dishes",dishes);
        addDishesToDatabase(dishes);
        model.addAttribute("dishes1",  getDishes());
        return "redirect:/admin";
    }
    @PostMapping(value = "dish/delete")
    public static String deleteDishes(@ModelAttribute Dishes dish1,Model model)throws SQLException{
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("dishes",new Dishes());
        model.addAttribute("dish1",dish1);
        deleteDishesFromDatabase(dish1);
        model.addAttribute("dishes1",getDishes());
        return "redirect:/admin";
    }

    @PostMapping(value = "dish/edit")
    public static String editDishes(@ModelAttribute Dishes dish,Model model)throws SQLException{
        editDishesFromDatabase(dish);
        model.addAttribute("dish",dish);
        return "dish";
    }

    @PostMapping(value = "personaladress/edit")
    public static String editPersonalAdress(@ModelAttribute PersonalAdress personaladress, Model model)throws SQLException{
        Authentication currentUsername = SecurityContextHolder.getContext().getAuthentication();
        personaladress.setUsername(currentUsername.getName());
        editPersonalAdressFun(personaladress);
        model.addAttribute("personaladress",personaladress);
        return "personaladressedit";
    }

    @PostMapping(value = "updatepersonaladress")
    public static String updatePersonalAdress(@ModelAttribute PersonalAdress personaladress,Model model)throws SQLException{
        Authentication currentUsername = SecurityContextHolder.getContext().getAuthentication();
        personaladress.setUsername(currentUsername.getName());
        model.addAttribute("personaladress",  getPersonalAdress(personaladress));
        updatePersonalAdressFun(personaladress);
        return "redirect:/user";
    }

    @PostMapping(value = "update")
    public static String updateDishes(@ModelAttribute Dishes dish,Model model)throws SQLException{
        updateDishesinDatabase(dish);
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("dishes",new Dishes());
        model.addAttribute("dish1",new Dishes());
        model.addAttribute("dishes1",getDishes());
        return "redirect:/admin";
    }

    @GetMapping(value="/user")
    public static String userPage(Model model) throws SQLException{
        Authentication currentUsername = SecurityContextHolder.getContext().getAuthentication();
        PersonalAdress data = new PersonalAdress(currentUsername.getName());
        try {
            pushPersonalAdress(data);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("personaladress",  getPersonalAdress(data));
        model.addAttribute("dishes1", ZamowienieLista(data.getUsername()));
        model.addAttribute("dishes",getDishes());
        model.addAttribute("dish3",new Dishes());
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("currentOrderTotalPrice",TotalPrice(data.getUsername()));

        return "user";
    }

    @GetMapping(value = "/default")
    public static String defaultPage(Model model) throws SQLException{

        Collection<? extends GrantedAuthority> authorities;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        authorities = auth.getAuthorities();
        String myRole = authorities.toArray()[0].toString();
        String admin = "ADMIN";
        if (myRole.equals(admin)) {
            model.addAttribute("id",0);
            model.addAttribute("dishes",new Dishes());
            model.addAttribute("dish1",new Dishes());
            model.addAttribute("dish2",new Dishes());
            model.addAttribute("dishes1",  getDishes());
            return "redirect:/admin";
        }

        Authentication currentUsername = SecurityContextHolder.getContext().getAuthentication();
        PersonalAdress data = new PersonalAdress(currentUsername.getName());
        try {
            pushPersonalAdress(data);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("personaladress",  getPersonalAdress(data));
        model.addAttribute("dishes1", ZamowienieLista(data.getUsername()));
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("dishes",getDishes());
        model.addAttribute("dish3",new Dishes());

        return "user";

    }
    @PostMapping(value = "dodaj")
    public static String dodajdozamowienia(@ModelAttribute Dishes dish,Model model)throws SQLException{
        Authentication currentUsername = SecurityContextHolder.getContext().getAuthentication();
        PersonalAdress data = new PersonalAdress(currentUsername.getName());

        //System.out.println(dish);
        //System.out.println(data.getID());
        model.addAttribute("personaladress",  getPersonalAdress(data));
        //System.out.println(data.getUsername());
        model.addAttribute("dishes",getDishes());
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("dish3",dodajDoZamówienia(dish,data.getUsername()));
        model.addAttribute("dishes1", ZamowienieLista(data.getUsername()));
        return "redirect:/user";

    }
    @PostMapping(value = "zamow")
    public static String zlozZamowienie(@ModelAttribute PersonalAdress pa,Model model)throws SQLException{
        Authentication currentUsername = SecurityContextHolder.getContext().getAuthentication();
        PersonalAdress data = new PersonalAdress(currentUsername.getName());

        model.addAttribute("personaladress",  getPersonalAdress(data));
        DodajZamowienie(data.getUsername());
        model.addAttribute("dishes1", ZamowienieLista(data.getUsername()));
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("dishes",getDishes());
        model.addAttribute("dish3",new Dishes());
        model.addAttribute("currentOrderTotalPrice",TotalPrice(data.getUsername()));

        return "redirect:/order";
    }
    @PostMapping(value = "anuluj")
    public static String anulujZamowienie(@ModelAttribute PersonalAdress pa,Model model)throws SQLException{
        Authentication currentUsername = SecurityContextHolder.getContext().getAuthentication();
        PersonalAdress data = new PersonalAdress(currentUsername.getName());
        model.addAttribute("personaladress",  getPersonalAdress(data));
        UsunZamowienie(data.getUsername());
        model.addAttribute("dishes1", ZamowienieLista(data.getUsername()));
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("dishes",getDishes());
        model.addAttribute("dish3",new Dishes());
        return "redirect:/user";
    }

    @RequestMapping(value = "/order")
    public static String orderPage(Model model) throws SQLException{
        Authentication currentUsername = SecurityContextHolder.getContext().getAuthentication();
        PersonalAdress data = new PersonalAdress(currentUsername.getName());

        model.addAttribute("personaladress",  getPersonalAdress(data));
        model.addAttribute("currentOrderTotalPrice",TotalPrice(data.getUsername()));

        return "order";
    }

    @PostMapping(value = "zamowIwyslijdobazydanych")
    public static String zamowIwyslijdobazydanych(@ModelAttribute PersonalAdress pa,Model model, RedirectAttributes redirAttrs)throws SQLException{
        Authentication currentUsername = SecurityContextHolder.getContext().getAuthentication();
        PersonalAdress data = new PersonalAdress(currentUsername.getName());

        model.addAttribute("personaladress",  getPersonalAdress(data));
        model.addAttribute("dishes1", ZamowienieLista(data.getUsername()));
        model.addAttribute("dish2",new Dishes());
        model.addAttribute("dishes",getDishes());
        model.addAttribute("dish3",new Dishes());


        //tutaj pobierz ustanienia i wyslij do bazy danych:

        if (1 != 1 ) { //tutaj dodac warunek gdy bedzie zle zamowienie
            redirAttrs.addFlashAttribute("error", "Twoje zamowienie ma bledy! Złoz jeszcze raz!");
        }
        else{
            redirAttrs.addFlashAttribute("success", "Twoje zamowienie zostało złozone poprawnie!");
        }
        return "redirect:/user";

    }

    @RequestMapping(value = "/admin-orders")
    public static String adminOrder(Model model) throws SQLException{
        model.addAttribute("zamowienia",adminOrderList());
        return "adminOrders";
    }


}

