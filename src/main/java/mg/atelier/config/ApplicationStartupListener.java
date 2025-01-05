package mg.atelier.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletContext;
import mg.atelier.model.Brand;
import mg.atelier.model.Clients;
import mg.atelier.model.ComponentType;
import mg.atelier.model.ComputerType;
import mg.atelier.model.Reparation;
import mg.atelier.model.ReparationTypePrice;
import mg.atelier.model.Status;
import mg.atelier.service.BrandService;
import mg.atelier.service.ClientsService;
import mg.atelier.service.ComponentService;
import mg.atelier.service.ComponentTypeService;
import mg.atelier.service.ComputerTypeService;
import mg.atelier.service.ReparationService;
import mg.atelier.service.ReparationTypePriceService;
import mg.atelier.service.StatusService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private final ComponentService componentService;
    @Autowired
    private final ClientsService clientsService;
    @Autowired
    private final ComponentTypeService componentTypeService;
    @Autowired
    private final ComputerTypeService computerTypeService;
    @Autowired
    private final ServletContext servletContext;
    @Autowired
    private final ReparationTypePriceService reparationTypePriceService;
    @Autowired
    private final BrandService brandService;
    @Autowired
    private final StatusService statusService;
    @Autowired
    private final ReparationService reparationService;

    public ApplicationStartupListener(ComponentService componentService, ComponentTypeService componentTypeService,
                                      ComputerTypeService computerTypeService, ClientsService clientService, ServletContext servletContext,
                                      ReparationTypePriceService reparationTypePriceService, BrandService brandService, StatusService statusService, ReparationService reparationService) {
        this.componentService = componentService;
        this.componentTypeService = componentTypeService;
        this.computerTypeService = computerTypeService;
        this.servletContext = servletContext;
        this.clientsService = clientService;
        this.reparationTypePriceService = reparationTypePriceService;
        this.brandService = brandService;
        this.statusService = statusService;
        this.reparationService = reparationService;
    }

    @Override
    public void onApplicationEvent(@SuppressWarnings("null") ContextRefreshedEvent event) {
        List<mg.atelier.model.ComponentStock> components = componentService.getAllComponentStocks();
        List<ComputerType> computerTypes = computerTypeService.getAllComputerTypes();
        List<ComponentType> componentTypes = componentTypeService.getAllComponentTypes();
        List<Clients> clients = clientsService.getAllClients();
        List<ReparationTypePrice> reparationTypes = reparationTypePriceService.getReparationTypePriceByDate(LocalDateTime.now());
        List<Reparation> reparations = reparationService.getAllReparations();
        List<Brand> brands = brandService.getAllBrands();
        List<Status> statuses = statusService.getAllStatuses();
        servletContext.setAttribute("components", components);
        servletContext.setAttribute("componentTypes", componentTypes);
        servletContext.setAttribute("computerTypes", computerTypes);
        servletContext.setAttribute("reparationTypes", reparationTypes);
        servletContext.setAttribute("clients", clients);
        servletContext.setAttribute("brands", brands);
        servletContext.setAttribute("statuses", statuses);
        servletContext.setAttribute("reparations", reparations);
    }
}