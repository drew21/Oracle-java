class finalProj
{

    static BufferedReader keyboard;  // Needed for keyboard I/O.
    static Connection conn; // A connection to the DB must be established
                            // before requests can be handled.  You should
                            // should have only one connection.
    static Statement stmt;  // Requests are sent via Statements.  You need
                            // one statement for everty request you have
                            // open at the same time.


    // "main" is where the connection to the database is made, and
    // where I/O is presented to allow the user to direct requests to
    // the methods that actually do the work.

public static void main (String args [])
        throws IOException
    {
        //String username = "USERNAME";
        String username ="8888888";
        //String password = "PASSWORD";
        String password = "88888888";
        String ename;
       int selection = 0;
        int  menuChoice = 12;
        int user =0;

        keyboard = new BufferedReader(new InputStreamReader (System.in));
        Scanner keyboardChoice = new Scanner(System.in);
        Scanner userKey = new Scanner(System.in);
        try { //Errors will throw a "SQLException" (caught below)

            // Load the Oracle JDBC driver
           DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            System.out.println("Registered the driver...");

            // Connect to the database.  The first argument is the
            // connection string, the second is your username, the third is
            // your password.
            conn = DriverManager.getConnection (
 "jdbc:oracle:thin:@toolman.wiu.edu:1521:orcl",
                        username, password);

            System.out.println("logged into oracle as " + username);
                while(user != 3) {      //Select User
                        // printCustTable();
                        System.out.print(" Which part of the program do you want to access? \n");
                        System.out.print("Enter 1 for Secretary or anyone involed in customer service. \n");
                        System.out.print("Enter 2 for Salesman. \n");
                        System.out.print("Enter 3 to exit \n");
                        //Scanner user = new Scanner(System.in);
                        user = userKey.nextInt();
                while( menuChoice != 0 && user !=3)
                {
                System.out.print("Enter 1 to Find all Advertisers \n");
                System.out.print("Enter 2 to Look up contracts or type of printing that was done by a certain employee  \n");
                System.out.print("Enter 3 to find Subscribers to a publication  \n");
                System.out.print("Enter 4 to update \n");
                System.out.print("Enter 5 to delete  \n");
                System.out.print("Enter 6 to insert  \n");


                //      System.out.print("Enter 7 to Look up publications that an advertiser has an ad space \n"); not working
                //      System.out.print("Enter 8 to view which Print Reqester  are also advertisers     \n"); not working
                //      System.out.print("Enter 9 to view Which Advertisers ad on a type of print    \n"); not working
                }
                System.out.print("Choose an operation(1-9) or Enter 0 to Exit:");
                //reads in the input for menu operations
                menuChoice = keyboardChoice.nextInt();
                if (menuChoice == 1)
                {
                        if( user ==1 )
                        {
                                System.out.println("ACCESS DENIED!");
                        }
                        else if (user ==2)
                        {
                        System.out.println("Look up Look up all advertisers");
                        lookUpAdvertisers();
                        }
                }
                else if (menuChoice == 2)
 {
                        System.out.println("Contracts for a certain Employee!");
                        //offersRecieved();
                         EmployeeContracts();
                }
                else if (menuChoice == 3)
                {
                        System.out.println("Subscribers to a publication.");
                       // horsepowerValues();
                        subSubcriptions();
                }
                else if (menuChoice == 4)
                {
                        System.out.println("Update!");
                        rowUpdate();
                }
          else if (menuChoice == 5)
                {
                        System.out.println("Delete!");
                        rowDelete();
                }
        else if (menuChoice == 6)
                {
                        System.out.println("Insert!");

                        insert();

                }
        /*
        else if (menuChoice == 7)
                {
                        System.out.println("Publications that an advertiser has an ad space!***NOT FUCTIONAL***");
                                  //not finished
                }
        else if (menuChoice == 8)
                {
                        System.out.println("view which businesses  are also advertisers!");
                                   //not finished
                }
        else if (menuChoice == 9)
                {
                        System.out.println("Enter 9 to view Which advertisers ad on a type of print\n");
                                 //not finished
                }

        */
        else if (menuChoice == 0)
                {
                         System.out.println("Exiting!");
                }
                else
                        System.out.println("Please pick a number between 1 and 9.");
               }        // }while ( menuChoice != 0 && user !=3); //end of do while

        }//end of outer while
            /*
            // Create a Statement; again, you may have/need more than one.
            stmt = conn.createStatement ();

                // Test the connection by printing out the names of your tables
                ResultSet rset = stmt.executeQuery("select * from tab");
                while (rset.next())
                System.out.println(rset.getString(1));
                */
            /***********************************************************************/
                //System.out.println("Exiting\n");
        } // ends the try
        catch(SQLException e)
            {
                System.out.println("Caught SQL Exception: \n     " + e);
            }//end of catch

    } //end of main

        //Queries

        public static void lookUpAdvertisers() throws IOException, SQLException
        {
                //String custID;
                try{
                //System.out.println("Enter a CustomerID(00001): ");
                stmt = conn.createStatement();
                //custID = keyboard.readLine();
               System.out.println("Here are your current advertisers.");
                ResultSet advertiser = stmt.executeQuery("Select CNAME, ADDRESS, CITY, STATE, ZIP "
                                                + "FROM mcm122.customers join mcm122.advertisers USING(customerID)");
                                                //+ "Where CustomerID = '" + custID+"'" );

                ResultSetMetaData rsmd = advertiser.getMetaData();
                int colcount = rsmd.getColumnCount();
                System.out.println("Name          Address           City       State     ZIP");
                System.out.println("------        ---------         --------   -----      ------");

                while(advertiser.next())
                {
                        for(int i =1; i <=colcount; i++)
                                System.out.print(advertiser.getString(i) + "  ");
                        System.out.println();
                }//end of while

                advertiser.close();
                }//end of try
                catch(IndexOutOfBoundsException ex)
                {
                        System.out.println("Caught IndexOutOfBoundException: \n   " + ex);
                }//end of catch

        }//end of lookUpadvertisers

        public static void EmployeeContracts() throws IOException, SQLException
        {
                String empID;
                try{
                        System.out.print("Enter an employee number (00001): ");
                        empID = keyboard.readLine();
                        stmt = conn.createStatement();
                        System.out.println("Here are all the contracts for "+empID+".");
                        ResultSet empContract = stmt.executeQuery("Select * " //EMPID, ContractAdID, AExpires, ContractID, Pexpires "
                                                + "FROM mcm122.contractsads, mcm122.contractsprinting "
                                                + "Where mcm122.contractsads.EMPID = "+empID+ " OR mcm122.contractsprinting.EMPID = "+empID);

                        ResultSetMetaData rsmd = empContract.getMetaData();
                        int colcount = rsmd.getColumnCount();
                        System.out.println("EMPID, ContractsAdsID, AExpires, ContractsPrintingID, Pexpires");
                        //System.out.println("ContactAdID          AExpires    EMPID   ContactsID  PExpires   EMPID");
                        System.out.println("-----   ---------       --------  -----      ------");

                while(empContract.next())
                {
                        for(int i =1; i <=colcount; i++)
                                System.out.print(empContract.getString(i) + "  ");
                        System.out.println();
                }//end of while

                empContract.close();
                }//end of try
                catch(IndexOutOfBoundsException ex)
                {
                        System.out.println("Caught IndexOutOfBoundException: \n   " + ex);
                }//end of catch


        }//end of EmployeeContracts

        public static void subSubcriptions() throws IOException, SQLException
        {
                String custID;
                try{
                        System.out.print("Enter a CustomerID (00001): ");
                        custID = keyboard.readLine();
                        stmt = conn.createStatement();
                        System.out.println("Here are all the Subscriber for "+custID+".");
                        ResultSet subSub = stmt.executeQuery("Select * "
                                                + "FROM mcm122.subscribers full join  mcm122.typeofprinting "
                                                + "On customerID = "+custID);

                        ResultSetMetaData rsmd = subSub.getMetaData();
                        int colcount = rsmd.getColumnCount();
                        System.out.println("Subscribers");
                        System.out.println("-----   ---------       --------  -----      ------");
                 while(subSub.next())
                {
                        for(int i =1; i <=colcount; i++)
                                System.out.print(subSub.getString(i) + "  ");
                        System.out.println();
                }//end of while

                subSub.close();
                }//end of try
                catch(IndexOutOfBoundsException ex)
                {
                        System.out.println("Caught IndexOutOfBoundException: \n   " + ex);
                }//end of catch

        }//end of SubSubscriptions

        public static void insert() throws IOException, SQLException
        {
                String CustID;
                String Name;
                String Address;
                String City;
                String State;
                String Zip;
                try{
                        System.out.print("Insert into customer table.");
                        System.out.print("Enter CustomerID:");
                        CustID = keyboard.readLine();
                        System.out.print("Enter Name:");
                        Name= keyboard.readLine();
                        System.out.print("Enter street address: ");
                        Address = keyboard.readLine();
                        System.out.print("Enter City:");
                        City = keyboard.readLine();
                        System.out.print("Enter State(IL):");
                        State = keyboard.readLine();
                        System.out.print("Enter Zip(61455)");
                        Zip = keyboard.readLine();

                        Statement InsStmt = conn.createStatement();
                        String InsCmd = "INSERT INTO mcm122.customers VALUES('"+CustID+"', '"+Name+"', '"+Address+"', '"+City+"', '"+State+"', '"+Zip+"')";
                        InsStmt.executeUpdate(InsCmd);
                        System.out.println("Insert complete!");
                        printCustTable();
                }//end of try

                catch(IndexOutOfBoundsException ex)
                {
                        System.out.println("Caught IndexOutOfBoundException: \n   " + ex);
                }//end of catch

        }//end of insert

        public static void rowDelete() throws IOException, SQLException
        {
                String CustID;
                try{
                        System.out.print("To delete a customer");
                        System.out.print("Enter CustomerID:");
                        CustID = keyboard.readLine();

                        Statement DelStmt = conn.createStatement();
                        String DelCmd = "DELETE FROM mcm122.customers WHERE customerID = '"+CustID+"'";
                        DelStmt.executeUpdate(DelCmd);
                        System.out.println("Delete Complete!");
                        printCustTable();
                }//end of try

                catch(IndexOutOfBoundsException ex)
                {
                        System.out.println("Caught IndexOutOfBoundException: \n   " + ex);
                }//end of catch

        }//end of rowDelete

        public static void rowUpdate() throws IOException, SQLException
        {
               String newName;
                String custID;
                try{
                        System.out.println("Update the name of the customer: ");
                        System.out.print("Enter the customerID: ");
                        custID = keyboard.readLine();
                        System.out.print("Enter New Name: ");
                        newName = keyboard.readLine();

                        Statement UpStmt = conn.createStatement();
                        String UpCmd = "Update mcm122.customers SET CNAME = '"+newName+"' WHERE CUSTOMERID = '"+custID+"'";
                        UpStmt.executeUpdate(UpCmd);
                        System.out.println("Update Complete");
                        printCustTable();
                }//end of try
                catch(IndexOutOfBoundsException ex)
                {
                        System.out.println("Caught IndexOutOfBoundException: \n   " + ex);
                }//end of catch

        }//end of rowUpdate

        public static void printCustTable() throws IOException, SQLException
        {
                try{
                        stmt = conn.createStatement();
                        ResultSet custTab = stmt.executeQuery("Select * from mcm122.customers");

                        ResultSetMetaData rsmd = custTab.getMetaData();
                        int colcount = rsmd.getColumnCount();
                        System.out.println("CSTID  NAME             Address          City          ST  Zip ");
                        System.out.println("-----  ---------        --------         -----         --  -----");
                while(custTab.next())
                {
                        for(int i =1; i <=colcount; i++)
                                System.out.print(custTab.getString(i) + "  ");
                        System.out.println();
                }//end of while


                }//end of try

                catch(IndexOutOfBoundsException ex)
                {
                        System.out.println("Caught IndexOutOfBoundException: \n   " + ex);
                }//end of catch

        }//end of printCustTable
}// end of lab4 class
