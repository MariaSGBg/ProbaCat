package com.dawes.controladores;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.dawes.modelo.Categoria;
import com.dawes.modelo.Ingrediente;
import com.dawes.modelo.Plato;
import com.dawes.serviciosImpl.ServicioCategoriaImpl;
import com.dawes.serviciosImpl.ServicioIngredienteImpl;
import com.dawes.serviciosImpl.ServicioPlatoImpl;
import com.dawes.serviciosImpl.UploadFileService;


@Controller
@RequestMapping("/platos")


				@SessionAttributes("idCategoria")


public class MainController {
	
	private static final int NUM_PRODUCTOS_ALEATORIOS = 3
			;
	@Autowired
	private ServicioPlatoImpl spi;
	@Autowired
	private ServicioCategoriaImpl sci;
	@Autowired
	private ServicioIngredienteImpl sii;
	@Autowired
	private UploadFileService upload;
	
		
//	@GetMapping("")
//	public String home(Model model) {	
//		model.addAttribute("platos", spi.findAll());
//		return "index";
//	}
	
	
//					@GetMapping("")					
//					public String home(@RequestParam(name = "idCategoria") Optional<Integer> idCategoria, Model model) {	
//						List<Plato> platos;
//				
//						if (!idCategoria.isPresent()) {
//							platos = spi.findAll();
//						} else {
//							platos = spi.findAllByCategoria(idCategoria.get());
//						}
//				
//						model.addAttribute("platosCat", platos);
//						model.addAttribute("platos", spi.findAll());
//						return "index";
//					}
	
	
	// Ok
				@GetMapping("")
				public String home(@RequestParam(name = "idCategoria", required=false) Integer idCategoria, Model model) {    
					model.addAttribute("categorias", sci.findAll());
					List<Plato> platos;
				 ///   List<Plato> platosCat;
			
				    System.err.println(idCategoria);
				    
				    if (idCategoria==null) {
//				        platos = spi.findAll();
				        platos = spi.obtenerPlatosAleatorios(NUM_PRODUCTOS_ALEATORIOS);
				  ///      platosCat = platos;
				    } else {
				 ///       platosCat = spi.findAllByCategoria(idCategoria);
				 ///       platos = spi.findAll();
				    					platos = spi.findAllByCategoria(idCategoria);
				    					
				    								model.addAttribute("idCategoria", idCategoria);
				    					
				    					
				        
				    }
				///    System.err.println("PLATOS CATEGORIA  "+idCategoria+ "---" + platosCat);
				///    model.addAttribute("platosCat", platosCat);
				    model.addAttribute("platos", platos);
				    model.addAttribute("idCategoria", idCategoria);
				    return "index";
				}
	
	
				// ??
//					@GetMapping("/")
//					public String index(@RequestParam(name="idCategoria", required=false) Integer idCategoria, Model model) {		
//						
//						model.addAttribute("categorias", sci.findAll());
//						
//						List<Plato> platos;
//						
//						if (idCategoria == null) {
//							platos = spi.findAll();
//						} else {			
//							platos = spi.findAllByCategoria(idCategoria);
//						}
//						
//						model.addAttribute("platos", platos);
//						model.addAttribute("idCategoria", idCategoria);  // Pass the idCategoria to the view
//						
//						return "index";
//					}
	
	
	// ??
//			 @GetMapping("/")
//			    public String index(@RequestParam(name = "idCategoria", required = false) Integer idCategoria, Model model) {
//			        model.addAttribute("categorias", sci.findAll());
//		
//			        List<Plato> platos;
//		
//			        if (idCategoria == null) {
//			            platos = spi.findAll();
//			        } else {
//			            platos = spi.findAllByCategoria(idCategoria);
//			        }
//		
//			        model.addAttribute("platos", platos);
//			        model.addAttribute("idCategoria", idCategoria);  // Pass the idCategoria to the view
//		
//			        return "index";
//			    }
				
				
				// ?? proba
//				@GetMapping("")
//				public String index(Model model) {
//					model.addAttribute("categorias", sci.findAll());
//					List<Plato> platos;
//					platos = spi.findAll();
//					model.addAttribute("platos", platos);
//					return "index";
//				}
				
				
				
				@GetMapping("/{id}")
				public String showDetails(@PathVariable("id") int id,  @ModelAttribute("idCategoria") Integer idCategoria, Model model) {
					Plato p = spi.findById(id).get();
					System.err.println("PLATO --> "+p.getImagen());
					if (p != null) {
						model.addAttribute("plato", p);
								model.addAttribute("idCategoria", idCategoria);
						return "detail";
					}
					
					return "redirect:/";
					
				}
				
	
	@GetMapping("/nuevoplato")
	public String create(Model model) {
		model.addAttribute("nuevoPlato", new Plato());
		model.addAttribute("catLista", sci.findAll());
		model.addAttribute("ingrLista", sii.findAll());
		return "form_add_plato";
	}
	
	@PostMapping("/saveplato")
	public String savePlato(@ModelAttribute("nuevoPlato") Plato plato, 
			BindingResult bindingResult, 
			@RequestParam("img") MultipartFile file
			) throws IOException {
		
		if (plato.getId()==null) {   // cuando se crea un nuevo plato
			String nombreImagen= upload.saveImage(file);
			plato.setImagen(nombreImagen);
			
		}
		
		
		 for (Ingrediente i : plato.getIngredientes()) {
	            i.getPlatos().add(plato);
	            plato.getIngredientes().add(i);    
	        }
		 
		 
//		plato.setCategoria(sci.findById(2).get());
		spi.save(plato);
		return "redirect:/platos";
	}
	
	
	
//	@PostMapping("/search")
//	public String searchProduct(@RequestParam String nombre, Model model) {
//		
//		// case-insensitive
//		List<Plato> platos = spi.findAll().stream()
//			    .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
//			    .collect(Collectors.toList());
//		System.err.println("P L A T O S "+platos);
//		model.addAttribute("platos", platos);		
//		return "index";
//
//	}
	
	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre, @RequestParam(required = false) Integer idCategoria, Model model) {
	    List<Plato> platos = spi.findAll().stream()
	        .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
	        .collect(Collectors.toList());
	    
	    model.addAttribute("platos", platos);
	    model.addAttribute("idCategoria", idCategoria);
	    return "index";
	}
	
	
	
	
	@ModelAttribute("categorias")
	public List<Categoria> todasCategorias() {
		return sci.findAll();
	}
	
}
