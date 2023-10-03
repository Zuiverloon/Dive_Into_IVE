package org.zjy.diveintoive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.zjy.diveintoive.Service.CatcherService;
import org.zjy.diveintoive.Service.CopyService;
import org.zjy.diveintoive.Service.HashService;
import org.zjy.diveintoive.Utils.ConstantUtil;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin
public class DiveintoiveApplication {

	private final Logger logger = LogManager.getLogger(DiveintoiveApplication.class);

	@Autowired
	CatcherService catcherService;
	@Autowired
	ConstantUtil constantUtil;

	@Autowired
	CopyService copyService;

	@Autowired
	HashService hashService;


	@GetMapping("/")
	public String home(){
		logger.info("Info Message");
		logger.debug("Debug Message!");
		logger.warn("Warn Message!");
		return "Hello World";
	}

	@GetMapping("/inscatcherstoragepath")
	public String getInsCatcherStoragePath(){
		return catcherService.getPath();
	}

	@PostMapping("/lizcopy")
	public int lizCopy(){
		return copyService.localCopy(constantUtil.LIZ);
	}

	@PostMapping("/gaeulcopy")
	public int gaeulCopy(){
		return copyService.localCopy(constantUtil.GAEUL);
	}

	@PostMapping("/lizgaeulcopy")
	public int lizgaeulCopy(){
		return copyService.localCopy(constantUtil.LIZ_GAEUL);
	}

	@GetMapping("/ive")
	public List<String> iveImages(){
		return catcherService.iveImages();
	}

	@PostMapping("/hash")
	public int genHash(){
		return hashService.genHash();
	}

	@GetMapping("/hash")
	public List<String> getHash(){
		return hashService.getHash();
	}

	@DeleteMapping("/hash")
	public int deleteHash(){
		return hashService.deleteHash();
	}


	public static void main(String[] args) {
		SpringApplication.run(DiveintoiveApplication.class, args);
	}




}
