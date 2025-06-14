package com.fromzero.deliverableservice.deliverables.application.internal.commandservices;


import com.fromzero.deliverableservice.deliverables.domain.model.aggregates.DefaultDeliverable;
import com.fromzero.deliverableservice.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.deliverableservice.deliverables.domain.model.commands.CreateDefaultDeliverableCommand;
import com.fromzero.deliverableservice.deliverables.domain.model.commands.SeedDefaultDeliverablesCommand;
import com.fromzero.deliverableservice.deliverables.domain.services.DefaultDeliverableCommandService;
import com.fromzero.deliverableservice.deliverables.domain.valueobjects.DeliverableStatus;
import com.fromzero.deliverableservice.deliverables.domain.valueobjects.ProjectTypeEnum;
import com.fromzero.deliverableservice.deliverables.infrastructure.persistence.jpa.repositories.DefaultDeliverableRepository;
import com.fromzero.deliverableservice.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultDeliverableCommandServiceImpl implements DefaultDeliverableCommandService {

    private final DefaultDeliverableRepository defaultDeliverableRepository;
    private final DeliverableRepository deliverableRepository;

    public DefaultDeliverableCommandServiceImpl(DefaultDeliverableRepository defaultDeliverableRepository, DeliverableRepository deliverableRepository) {
        this.defaultDeliverableRepository = defaultDeliverableRepository;
        this.deliverableRepository = deliverableRepository;
    }


    @Override
    public void handle(SeedDefaultDeliverablesCommand command) {
        ProjectTypeEnum type = command.projectTypeEnum();
        List<DefaultDeliverable> deliverables = new ArrayList<>();

        switch (type) {
            case LANDING_PAGE -> {
                deliverables.add(new DefaultDeliverable(type, "Definiendo requisitos del proyecto", "Reunión para especificar usuarios y propósito", 1, 1));
                deliverables.add(new DefaultDeliverable(type, "Estructura y contenidos", "Wireframes, mockups y recursos visuales", 2, 2));
                deliverables.add(new DefaultDeliverable(type, "Desarrollo inicial", "Primera versión funcional del sitio", 3, 3));
                deliverables.add(new DefaultDeliverable(type, "Validación UX", "Evaluación de usabilidad y mejoras", 4, 4));
                deliverables.add(new DefaultDeliverable(type, "Optimización SEO", "Implementación de posicionamiento básico", 1, 5));
                deliverables.add(new DefaultDeliverable(type, "Entrega final", "Despliegue y documentación técnica", 5, 6));
            }
            case ECOMMERCE -> {
                deliverables.add(new DefaultDeliverable(type, "Análisis del negocio", "Recolección de requisitos y métodos de pago", 1, 1));
                deliverables.add(new DefaultDeliverable(type, "Diseño UI/UX", "Wireframes del portal de ventas", 2, 2));
                deliverables.add(new DefaultDeliverable(type, "Desarrollo del catálogo", "Implementación de productos y filtros", 3, 3));
                deliverables.add(new DefaultDeliverable(type, "Integración de pagos", "Configuración de pasarelas de pago", 4, 4));
                deliverables.add(new DefaultDeliverable(type, "Pruebas completas", "Flujo de compra completo", 5, 5));
                deliverables.add(new DefaultDeliverable(type, "Lanzamiento", "Entrega final y despliegue", 6, 6));
            }
            case ONE_PAGE_WEBSITE -> {
                deliverables.add(new DefaultDeliverable(type, "Definición de contenido", "Secciones clave como inicio, servicios, contacto", 1, 1));
                deliverables.add(new DefaultDeliverable(type, "Diseño del flujo", "Wireframes y navegación vertical", 2, 2));
                deliverables.add(new DefaultDeliverable(type, "Primera versión", "Contenido simulado y diseño responsivo", 3, 3));
                deliverables.add(new DefaultDeliverable(type, "Optimización visual", "Animaciones suaves y mejoras visuales", 4, 4));
                deliverables.add(new DefaultDeliverable(type, "Validación", "Revisión de contenido real", 5, 5));
                deliverables.add(new DefaultDeliverable(type, "Publicación", "Versión final en producción", 6, 6));
            }
            case MOBILE_APPLICATION -> {
                deliverables.add(new DefaultDeliverable(type, "Definición de plataformas", "Android, iOS o híbrida", 1, 1));
                deliverables.add(new DefaultDeliverable(type, "Prototipo navegable", "Diseño UX en Figma u otra herramienta", 2, 2));
                deliverables.add(new DefaultDeliverable(type, "Desarrollo MVP", "Funcionalidades mínimas operativas", 3, 3));
                deliverables.add(new DefaultDeliverable(type, "Integración de servicios", "APIs y backend", 4, 4));
                deliverables.add(new DefaultDeliverable(type, "Pruebas en dispositivos", "Validación multiplataforma", 5, 5));
                deliverables.add(new DefaultDeliverable(type, "Publicación", "Subida a Play Store / App Store", 6, 6));
            }
            case DESKTOP_APPLICATION -> {
                deliverables.add(new DefaultDeliverable(type, "Requisitos funcionales", "Módulos, usuarios, entornos", 1, 1));
                deliverables.add(new DefaultDeliverable(type, "Diseño de interfaz", "Ventanas, navegación, menús", 2, 2));
                deliverables.add(new DefaultDeliverable(type, "MVP funcional", "Persistencia local y navegación", 3, 3));
                deliverables.add(new DefaultDeliverable(type, "Pruebas funcionales", "Módulos críticos", 4, 4));
                deliverables.add(new DefaultDeliverable(type, "Documentación técnica", "Manual de despliegue y mantenimiento", 5, 5));
                deliverables.add(new DefaultDeliverable(type, "Publicación", "Instalador y entrega final", 6, 6));
            }
        }

        // to avoid duplicates every time the project is executed. Check if there are already deliverables for the project type, if not, save the default deliverables
        if (defaultDeliverableRepository.countByProjectTypeEnum(type) == 0) {
            defaultDeliverableRepository.saveAll(deliverables);
        }

    }

    @Override
    public void handle(CreateDefaultDeliverableCommand command) {
        ProjectTypeEnum projectType = ProjectTypeEnum.valueOf(command.projectType());
        String projectId = command.projectId();

        List<DefaultDeliverable> defaults = defaultDeliverableRepository.findByProjectTypeEnum(projectType);

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        List<Deliverable> deliverables = defaults.stream().map(d -> {
            String deadline = today.plusWeeks(d.getWeeksToComplete()).format(formatter) + "T23:59:59";
            return new Deliverable(
                    d.getName(),
                    d.getDescription(),
                    deadline,
                    projectId,
                    d.getOrderNumber(),
                    DeliverableStatus.PENDING
            );
        }).toList();

        deliverableRepository.saveAll(deliverables);
    }

}
