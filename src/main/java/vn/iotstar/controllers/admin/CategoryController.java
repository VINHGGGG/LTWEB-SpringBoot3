package vn.iotstar.controllers.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import vn.iotstar.entity.Category;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("")
	public String All(Model model) {
		List<Category> list = categoryService.findAll();
		
		model.addAttribute("list", list);
		return "admin/category/list";
	}
	
	@GetMapping("/add")
	public String Add(Model model) {
		CategoryModel cate = new CategoryModel();
		cate.setIsEdit(false);
		model.addAttribute("category", cate);
		return "admin/category/add";
	}
	
	@PostMapping("/save")
	public ModelAndView SaveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryModel catemodel, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/category/add");
		}
		
		Category entity = new Category();
		
		// Copy from Model to Entity
		BeanUtils.copyProperties(catemodel, entity);
		
		// Gọi hàm Save trong Service
		categoryService.save(entity);
		
		// Đưa thông báo về cho biến Message
		String message = "";
		if (catemodel.getIsEdit() == true) {
			message = "Category is Edited";
		}
		else {
			message = "Category is Saved";
		}
		
		model.addAttribute("message", message);
		
		// Redirect về URL Controller
		return new ModelAndView("forward:/admin/categories", model);
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView Edit(ModelMap model, @PathVariable("id") Long categoryid) {
		Optional<Category> optCategory = categoryService.findById(categoryid);
		CategoryModel cateModel = new CategoryModel();
		
		// Kiểm tra sự tồn tại của Category
		if (optCategory.isPresent()) {
			Category entity = optCategory.get();
			
			// Copy from Entity to CateModel
			BeanUtils.copyProperties(entity, cateModel);
			cateModel.setIsEdit(true);
			
			// Đẩy dữ liệu ra view
			model.addAttribute("category", cateModel);
			
			return new ModelAndView("admin/category/add", model);
		}
		
		model.addAttribute("message", "Category is not existed");
		return new ModelAndView("forward:/admin/categories", model);
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView Delete(ModelMap model, @PathVariable("id") Long categoryid) {
		categoryService.deleteById(categoryid);		
		model.addAttribute("message", "Category is deleted");
		return new ModelAndView("forward:/admin/categories", model);
	}
	
}