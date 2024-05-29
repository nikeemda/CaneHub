package edu.bus.bte324.canehub.utilities;

import java.util.Scanner;

import edu.bus.bte324.canehub.infoclasses.MenuInfo;

public class MenuInputUtility {

    public static MenuInfo getMenuInfoFromConsole()
    {
        System.out.println("Input the New Item");
        Scanner menuInfoScanner = new Scanner(System.in);
        System.out.print("Input Item Name:");
        String itemName = menuInfoScanner.nextLine();
        System.out.print("Input Item Price:");
        double price = menuInfoScanner.nextDouble();
        menuInfoScanner.nextLine();
        System.out.print("Input Description of Item: ");
        String description = menuInfoScanner.nextLine();
        


        MenuInfo menuInfo = new MenuInfo(itemName,price,description);
        return  menuInfo;
    }
    
}
