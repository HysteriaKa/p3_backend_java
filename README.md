# Estate

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.0.

## Start the project

Git clone:

> git clone https://github.com/HysteriaKa/p3_backend_java

### Run back-end
To integrate an existing Maven Java project into IDEA, follow these steps:

1. Open IntelliJ IDEA and close any existing project.

2. From the Welcome screen, click Import Project.
The Select File or Directory to Import dialog opens.

3. Navigate to your Maven project and select the top-level folder. For example, with a project named "P3_backend_java":

4. Click OK.
5. For the Import project from external model value, select Maven and click Next.
6. Select Import Maven projects automatically and leave the other fields with default values.
7. Click Next.
8. Next
The Please select project SDK screen opens.
9. Make sure the correct JDC is selected and click Next.
10. Change the project name and location if you like, and click Finish.
11. Create a run configuration to test your project:
- From the Run menu, select Run.
- Click Edit Configurations….
- Make sure Maven is selected, and click + (Add New Configuration).
- Select Maven
- Give your configuration a name.
- In the Comand line: field, enter lagom:runAll.
- Click OK.

12.Build your project and run it using the configuration you created.

## Front-end
Don't forget to install your node_modules before starting (`npm install`). 
> npm install

Launch Front-end:

> npm run start;

### MySQL

SQL script for creating the schema is available `ressources/sql/script.sql`
