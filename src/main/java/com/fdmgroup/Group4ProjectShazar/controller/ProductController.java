package com.fdmgroup.Group4ProjectShazar.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.Group4ProjectShazar.model.Product;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.BookingRepository;
import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
import com.fdmgroup.Group4ProjectShazar.service.ProductService;
import com.fdmgroup.Group4ProjectShazar.service.ShowProductService;
import com.fdmgroup.Group4ProjectShazar.util.Filters;
import com.fdmgroup.Group4ProjectShazar.util.ProductPictureUtil;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private MainController mainController;

	@Autowired
	private DefaultUserDetailService userService;
	
	@Autowired
	private ShowProductService showProductService;


	@GetMapping(value = "/goToProductPage/{productId}")
	public String goToProductPage(ModelMap model, @PathVariable int productId) {
		mainController.populateLoggedUserModel(model);
		Product product = service.findProductById(productId);
		model.addAttribute("product", product);
		return "product";
	}

	@GetMapping(value = "/goToAddProduct")
	public String goToAddProduct(ModelMap model) {
		mainController.populateLoggedUserModel(model);
		return "addProduct";
	}

	@PostMapping("/addProduct")
	public String addNewProduct(ModelMap model, @RequestParam String name, @RequestParam String description,
			@RequestParam String pickupTime, @RequestParam double priceForDay, @RequestParam int maxNumberOfDays,
			@RequestParam String color, @RequestParam String type, @RequestParam String category,
			@RequestParam String user, @RequestParam("main") MultipartFile mainMultipartFile,
			@RequestParam("additionalImage") MultipartFile[] additionalMultipartFiles) throws IOException {

		User owner = userService.findByUsername(user);
		Product newProduct = new Product();
		newProduct.setName(name);
		newProduct.setDescription(description);
		newProduct.setIsAvailableNow(true);
		newProduct.setPickupTime(pickupTime);
		newProduct.setPriceForDay(priceForDay);
		newProduct.setMaxNumberOfDays(maxNumberOfDays);
		newProduct.setColor(color);
		newProduct.setType(type);
		newProduct.setCategory(category);
		newProduct.setOwner(owner);
		newProduct.setPickupAdress(owner.getAddress());

		String mainImageName = StringUtils.cleanPath(mainMultipartFile.getOriginalFilename());
		newProduct.setMainImage(mainImageName);

		int count = 0;
		for (MultipartFile additionalMultipart : additionalMultipartFiles) {
			String additionalImageName = StringUtils.cleanPath(additionalMultipart.getOriginalFilename());

			if (count == 0)
				newProduct.setImage2(additionalImageName);
			if (count == 1)
				newProduct.setImage3(additionalImageName);
			if (count == 2)
				newProduct.setImage4(additionalImageName);
			if (count == 3)
				newProduct.setImage5(additionalImageName);
			if (count == 4)
				newProduct.setImage6(additionalImageName);
			if (count == 5)
				newProduct.setImage7(additionalImageName);
			if (count == 6)
				newProduct.setImage8(additionalImageName);
			if (count == 7)
				newProduct.setImage9(additionalImageName);
			if (count == 8)
				newProduct.setImage10(additionalImageName);

			count++;
		}

		service.createNewProduct(newProduct);
		model.addAttribute("product", newProduct);
		mainController.populateLoggedUserModel(model);

		String uploadDir = "./product-pictures/" + newProduct.getProductId();

		ProductPictureUtil.savePicture(uploadDir, mainImageName, mainMultipartFile);

		for (MultipartFile additionalMultipart : additionalMultipartFiles) {
			String fileName = StringUtils.cleanPath(additionalMultipart.getOriginalFilename());
			ProductPictureUtil.savePicture(uploadDir, fileName, additionalMultipart);
		}

		return "product";
	}

	@GetMapping(value = "/goToEditProduct/{productId}")
	public String goToEditProduct(ModelMap model, @PathVariable int productId) {
		Product product = service.findProductById(productId);
		model.addAttribute("product", product);
		mainController.populateLoggedUserModel(model);
		return "editProduct";
	}

	@PostMapping("/editProduct")
	public String editProduct(ModelMap model, @RequestParam String name, @RequestParam String description,
			@RequestParam String pickupTime, @RequestParam double price, @RequestParam int maxDays,
			@RequestParam String color, @RequestParam String type, @RequestParam String category,
			@RequestParam("main") MultipartFile mainMultipartFile,
			@RequestParam("additionalImage") MultipartFile[] additionalMultipartFiles, @RequestParam int productId)
			throws IOException {

		Product product = service.findProductById(productId);

		product.setName(name);
		product.setDescription(description);
		product.setPickupTime(pickupTime);
		product.setPriceForDay(price);
		product.setMaxNumberOfDays(maxDays);
		product.setColor(color);
		product.setType(type);
		product.setCategory(category);

		String mainImageName = StringUtils.cleanPath(mainMultipartFile.getOriginalFilename());
		product.setMainImage(mainImageName);

		int count = 0;
		for (MultipartFile additionalMultipart : additionalMultipartFiles) {
			String additionalImageName = StringUtils.cleanPath(additionalMultipart.getOriginalFilename());

			if (count == 0)
				product.setImage2(additionalImageName);
			if (count == 1)
				product.setImage3(additionalImageName);
			if (count == 2)
				product.setImage4(additionalImageName);
			if (count == 3)
				product.setImage5(additionalImageName);
			if (count == 4)
				product.setImage6(additionalImageName);
			if (count == 5)
				product.setImage7(additionalImageName);
			if (count == 6)
				product.setImage8(additionalImageName);
			if (count == 7)
				product.setImage9(additionalImageName);
			if (count == 8)
				product.setImage10(additionalImageName);

			count++;
		}

		service.save(product);
		model.addAttribute("product", product);
		mainController.populateLoggedUserModel(model);

		String uploadDir = "./product-pictures/" + product.getProductId();

		ProductPictureUtil.savePicture(uploadDir, mainImageName, mainMultipartFile);

		for (MultipartFile additionalMultipart : additionalMultipartFiles) {
			String fileName = StringUtils.cleanPath(additionalMultipart.getOriginalFilename());
			ProductPictureUtil.savePicture(uploadDir, fileName, additionalMultipart);
		}
		return "product";
	}

	@GetMapping("/goToSearchingPage")
	public String toSearchingPage(ModelMap model, @RequestParam String productName, @RequestParam String startDate,
			@RequestParam String endDate, @RequestParam String city) {

		
		//error checks
		if (productName.isEmpty()) {
			model.addAttribute("errorNothingToSearch", "Please type something to search");
			List<Product> listOfTopProducts = showProductService.listTopSixProducts();
			model.addAttribute("topProducts", listOfTopProducts.stream().limit(6).toList());
			mainController.populateLoggedUserModel(model);
			return "index";
		}

		if (startDate.isEmpty() || endDate.isEmpty()) {
			model.addAttribute("errorNoDate", "Please choose date");
			List<Product> listOfTopProducts = showProductService.listTopSixProducts();
			model.addAttribute("topProducts", listOfTopProducts.stream().limit(6).toList());
			mainController.populateLoggedUserModel(model);
			return "index";
		}

		// we change input from user which is String to LocalDate using
		// DateTimeFormatter
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate startDateLD = LocalDate.parse(startDate, dateTimeFormatter);
		LocalDate endDateLD = LocalDate.parse(endDate, dateTimeFormatter);

		// assuring that the reservation cannot be made in the past and that the end
		// date is not before start date.
		if (endDateLD.isBefore(LocalDate.now()) || startDateLD.isBefore(LocalDate.now())
				|| endDateLD.isBefore(startDateLD)) {
			model.addAttribute("errorDate", "Invalid date");
			List<Product> listOfTopProducts = showProductService.listTopSixProducts();
			model.addAttribute("topProducts", listOfTopProducts.stream().limit(6).toList());
			mainController.populateLoggedUserModel(model);
			return "index";
		}

		// get products by name and subtract from them already booked on this termin
		List<Product> foundProducts = service.subtractList(service.findProductsByName(productName),
				service.findProductsAlreadyBooked(productName, startDate, endDate));
		service.filterByLocation(foundProducts, city);

		model.addAttribute("city", city);
		model.addAttribute("foundProducts", foundProducts);
		model.addAttribute("searchedPhrase", productName);
		model.addAttribute("filters", new Filters());
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("location", city);

		mainController.populateLoggedUserModel(model);

		return "searching-page";
	}

	@PostMapping("/filtered")
	public String filterSearch(ModelMap model, @RequestParam String searchedPhrase, @RequestParam String color,
			@RequestParam String category, @RequestParam String type, @RequestParam String minPrice,
			@RequestParam String maxPrice, @RequestParam String city, @RequestParam String startDate,
			@RequestParam String endDate) {

		// check if there are mi nand max values typed, if yes then check if min <max

		if (!minPrice.isEmpty() && !maxPrice.isEmpty() && Double.valueOf(minPrice) > Double.valueOf(maxPrice)) {
			model.addAttribute("errorMessage", "Min value cannot be higher than max.");
			model.addAttribute("filters", new Filters());
			model.addAttribute("searchedPhrase", searchedPhrase);
			mainController.populateLoggedUserModel(model);
			return "searching-page";
		}

		Filters filters = new Filters(color, category, minPrice, maxPrice, type);
		List<Product> filteredProducts = service.subtractList(service.findProductsByName(searchedPhrase),
				service.findProductsAlreadyBooked(searchedPhrase, startDate, endDate));

		filteredProducts = service.filterResults(filteredProducts, filters, city);

		model.addAttribute("foundProducts", filteredProducts);
		model.addAttribute("searchedPhrase", searchedPhrase);
		model.addAttribute("filters", filters);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("city", city);
		mainController.populateLoggedUserModel(model);

		return "searching-page";
	}
}
