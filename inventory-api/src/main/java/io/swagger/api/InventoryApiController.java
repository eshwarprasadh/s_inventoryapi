package io.swagger.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.threeten.bp.OffsetDateTime;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import io.swagger.model.InventoryItem;
import io.swagger.model.Manufacturer;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-06T06:45:29.220Z")

@Controller
public class InventoryApiController implements InventoryApi {

    private static final Logger log = LoggerFactory.getLogger(InventoryApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private static final List<InventoryItem> inventoryItems;
    
    static {
    	inventoryItems = new ArrayList<InventoryItem>();
		InventoryItem inventoryItem1 = new InventoryItem();
		inventoryItem1.setId(UUID.randomUUID());
		inventoryItem1.setName("Widget Adapter");
		inventoryItem1.setReleaseDate(OffsetDateTime.now());
		inventoryItem1.setManufacturer(new Manufacturer() {
			@Override
			public void setName(String name) {
				super.setName("ACME Corporation");
			}
			
			@Override
			public void setPhone(String phone) {
				super.setPhone("408-867-5309");
			}
			
			@Override
			public void setHomePage(String homePage) {
				super.setHomePage("https://www.acme-corp.com");
			}
		});
		InventoryItem inventoryItem2 = new InventoryItem();
		inventoryItem2.setId(UUID.randomUUID());
		inventoryItem2.setName("Docker");
		inventoryItem2.setReleaseDate(OffsetDateTime.now());
		inventoryItem2.setManufacturer(new Manufacturer() {
			@Override
			public void setName(String name) {
				super.setName("ACME Corporation");
			}
			
			@Override
			public void setPhone(String phone) {
				super.setPhone("408-867-5309");
			}
			
			@Override
			public void setHomePage(String homePage) {
				super.setHomePage("https://www.acme-corp.com");
			}
		});
		InventoryItem inventoryItem3 = new InventoryItem();
		inventoryItem3.setId(UUID.randomUUID());
		inventoryItem3.setName("Switch");
		inventoryItem3.setReleaseDate(OffsetDateTime.now());
		inventoryItem3.setManufacturer(new Manufacturer() {
			@Override
			public void setName(String name) {
				super.setName("ACME Corporation");
			}
			
			@Override
			public void setPhone(String phone) {
				super.setPhone("408-867-5309");
			}
			
			@Override
			public void setHomePage(String homePage) {
				super.setHomePage("https://www.acme-corp.com");
			}
		});
		inventoryItems.add(inventoryItem1);
		inventoryItems.add(inventoryItem2);
		inventoryItems.add(inventoryItem3);
    }

    @org.springframework.beans.factory.annotation.Autowired
    public InventoryApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addInventory(@ApiParam(value = "Inventory item to add"  )  @Valid @RequestBody InventoryItem inventoryItem) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<InventoryItem>> searchInventory(@ApiParam(value = "pass an optional search string for looking up inventory") @Valid @RequestParam(value = "searchString", required = false) String searchString,@Min(0)@ApiParam(value = "number of records to skip for pagination") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        List<InventoryItem> invItems = null;
        if (accept != null && accept.contains("application/json")) {
            try {
            	invItems = getInventories(searchString);
            	if(!invItems.isEmpty())
            		return new ResponseEntity<List<InventoryItem>>(invItems, HttpStatus.OK);
            	else
            		return new ResponseEntity<List<InventoryItem>>(invItems, HttpStatus.BAD_REQUEST);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<InventoryItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<InventoryItem>>(HttpStatus.NOT_IMPLEMENTED);
    }

	private List<InventoryItem> getInventories(String searchString) throws IOException, JsonParseException, JsonMappingException {
		searchString = searchString.toLowerCase();
		List<InventoryItem> inventoryItemsSrchResult = new ArrayList<>();
		for(InventoryItem inventoryItem : inventoryItems) {
			String name = inventoryItem.getName() != null ? inventoryItem.getName().toLowerCase() : "";
			if(name.contains(searchString)) {
				 inventoryItemsSrchResult.add(inventoryItem);
			}
		}
		
		return inventoryItemsSrchResult;
	}

}
