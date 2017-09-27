package net.mstezala.configurations;

import net.mstezala.mvc.controllers.*;
import net.mstezala.services.*;
import net.mstezala.services.implementations.MachineServiceImpl;
import net.mstezala.services.implementations.TicketServiceImpl;


public class ApplicationConfiguration {
    public static final MachineService machineService = MachineServiceImpl.SingletonHolder.getInstance();
    public static final TicketService ticketService = TicketServiceImpl.SingletonHolder.getInstance();

    public static final HomeController homeController = HomeController.SingletonHolder.getInstance();
    public static final TicketController ticketController = TicketController.SingletonHolder.getInstance();
    public static final MachineController machineController = MachineController.SingletonHolder.getInstance();
}
