package com.dawes.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dawes.modelo.Menu;
import com.dawes.modelo.MenuPlato;
import com.dawes.modelo.Plato;
import com.dawes.servicios.ServicioMenu;
import com.dawes.serviciosImpl.ServicioCategoriaImpl;
import com.dawes.serviciosImpl.ServicioIngredienteImpl;
import com.dawes.serviciosImpl.ServicioPlatoImpl;

@Controller
@RequestMapping("/menus")
public class MenuController {
	@Autowired
	private ServicioMenu smi;
	@Autowired
	private ServicioPlatoImpl spi;
	@Autowired
	private ServicioCategoriaImpl sci;
	@Autowired
	private ServicioIngredienteImpl sii;
	
	@GetMapping("")
	public String home(Model model) {    
//		model.addAttribute("categorias", sci.findAll());
		List<Menu> menus;
//	    List<Plato> platosCat;
	        menus = smi.findAll();
	   
	    model.addAttribute("menus", menus);
	    return "menus";
	}
	
	@GetMapping("/nuevomenu")
	public String create(Model model) {
		model.addAttribute("nuevoMenu", new Menu());
		
		model.addAttribute("platosLista", spi.findAll());
		return "form_add_menu";
	}
	
//	@PostMapping("/savemenu")
//	public String saveMenu(@ModelAttribute("nuevoMenu") Menu menu, 
//			BindingResult bindingResult, 
//			@RequestParam("img") MultipartFile file
//			) throws IOException {
//		
////		if (menu.getId()==null) {   // cuando se crea un nuevo plato
////			String nombreImagen= upload.saveImage(file);
////			plato.setImagen(nombreImagen);
////			
////		}
//		
//	System.err.println("M E N U " + menu);
//		 for (MenuPlato mp : menu.getPlatos()) {
//			 mp.setDia(1);
//	            mp.setMenu(menu);
//	            mp.setPlato(mp.getPlato());    
//	            
//	        }
//		 
//		 
////		plato.setCategoria(sci.findById(2).get());
//		smi.save(menu);
//		return "redirect:/menus";
//	}
	
	
	
//	@PostMapping("/savemenu")
//	public String saveMenu(@ModelAttribute("nuevoMenu") Menu menu, 
//	        BindingResult bindingResult, 
//	        @RequestParam("img") MultipartFile file) throws IOException {
//
//	    System.err.println("M E N U " + menu);
//
//	    if (menu.getPlatos() != null) {
//	        for (MenuPlato mp : menu.getPlatos()) {
//	            if (mp != null) {
//	                mp.setDia(1);
//	                mp.setMenu(menu);
//	                mp.setPlato(mp.getPlato());
//	            } else {
//	                System.err.println("Found null MenuPlato in the list");
//	            }
//	        }
//	    } else {
//	        System.err.println("Menu has null platos");
//	    }
//	    
//	    // Save the menu
//	    smi.save(menu);
//
//	    return "redirect:/menus";
//	}
	
//						@PostMapping("/savemenu")
//						public String saveMenu(@ModelAttribute("nuevoMenu") Menu menu, 
//						        BindingResult bindingResult, 
//						        @RequestParam("platos") List<Integer> platoIds,
//						        @RequestParam("img") MultipartFile file) throws IOException {
//					
//						    System.err.println("M E N U " + menu);
//					
//						    // Create MenuPlato objects from the selected plato IDs
//						    Set<MenuPlato> menuPlatos = new HashSet<>();
//						    for (Integer platoId : platoIds) {
//						        MenuPlato mp = new MenuPlato();
//						        Plato plato = new Plato(); // Assuming Plato class has a setter for ID
//						        plato.setId(platoId);
//						        mp.setPlato(plato);
//						        mp.setDia(1);
//						        mp.setMenu(menu);
//						        menuPlatos.add(mp);
//						    }
//						    menu.setPlatos(menuPlatos);
//					
//						    // Save the menu
//						    smi.save(menu);
//					
//						    return "redirect:/menus";
//						}
	
	
	@PostMapping("/savemenu")
	public String saveMenu(@ModelAttribute("nuevoMenu") Menu menu, 
	                       BindingResult bindingResult, 
	                       @RequestParam("platos") List<Integer> platoIds,
	                       @RequestParam("img") MultipartFile file) throws IOException {

		
		System.err.println("IDS ------------> "+platoIds);  // ok  -1,2

		
	    List<MenuPlato> menuPlatos = new ArrayList<>();
	    for (Integer platoId : platoIds) {
	    	System.err.println(platoId);
	        MenuPlato mp = new MenuPlato();
	        Plato plato = new Plato();
	        plato.setId(platoId);
	        mp.setPlato(plato);
	        mp.setDia(1);
	        menu.addPlato(mp);  // Use the addPlato method to set the relationship
	    }

	    smi.save(menu);

	    return "redirect:/menus";
	}


	@GetMapping("/{id}")
	public String showMenuDetails(@PathVariable("id") int id, Model model) {
		Menu m = smi.findById(id).get();
		if (m != null) {
			model.addAttribute("menu", m);
			return "detail_menu";
		}
		
		return "redirect:/";
	}
	

}
