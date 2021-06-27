import java.util.*;
import java.lang.*;

public class Main
{
    public static class Patient
    {
        public int id;
        public String name;
        public float temp;
        public int oxy;
        public int age;

        public int assigned = 0;
        public int deleted = 0;
        public int recdays;
        public String institutename = " ";

        Patient(int id,String name,float temp,int oxy,int age)
        {
            this.id = id;
            this.name = name;
            this.temp = temp;
            this.oxy = oxy;
            this.age = age;
        }

        public void display()
        {
            System.out.print("PATIENT DETAILS:");
            System.out.println();
            System.out.println();
            System.out.println("    Patient Name: " + name);
            System.out.println("    Temperature: " + temp);
            System.out.println("    Oxygen Level: " + oxy);
            System.out.println("    Age: " + age);
            if(assigned==1)
            {
                System.out.println("    Admission Status: ADMITTED");
                System.out.println("    Admitting Institute: " + institutename);
            }
            else
            {
                System.out.println("    Admission Status: NOT ADMITTED");
            }
        }
    }

    public static class Institute
    {
        public String name;
        public float tempcrite;
        public int oxycrite;
        public int bedavail;
        public int beds;

        public int admissionstatus = 1;
        public int deleted = 0;

        public Institute(String name,float tempcrite,int oxycrite,int bedavail)
        {
            this.name = name;
            this.tempcrite = tempcrite;
            this.oxycrite = oxycrite;
            this.bedavail = bedavail;
            this.beds = bedavail;

            this.checkStatus();
        }

        public void checkStatus()
        {
            if(bedavail>0)
            {
                admissionstatus = 1;
            }
            else
            {
                admissionstatus = 0;
            }
        }

        public void display()
        {
            System.out.print("INSTITUTE DETAILS:");
            System.out.println();
            System.out.println();
            this.checkStatus();
            System.out.println("    Institute Name: " + name);
            System.out.println("    Temperature should be <= " + tempcrite);
            System.out.println("    Oxygen Level should be >= " + oxycrite);
            System.out.println("    Beds Available: " + bedavail);

            if(admissionstatus==1)
            {
                System.out.println("    Admission Status: OPEN");
            }
            else
            {
                System.out.println("    Admission Status: CLOSED");
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner inp = new Scanner(System.in);

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("Enter Number of Patients to be Admitted: ");
        int nPatients = inp.nextInt();
        String nm = inp.nextLine();

        Patient patientsobj[] = new Patient[nPatients];
        ArrayList<Institute> instituteobj = new ArrayList<>();

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("ENTER PATIENT DETAILS:");
        for(int i=0;i<nPatients;i++)
        {
            System.out.println("\nPatient " + (i+1) + ":");
            System.out.print("    Name: ");
            nm = inp.nextLine();
            System.out.print("    Temperature: ");
            float temp = inp.nextFloat();
            System.out.print("    Oxygen Level: ");
            int oxy = inp.nextInt();
            System.out.print("    Age: ");
            int age = inp.nextInt();

            patientsobj[i] = new Patient(i+1,nm,temp,oxy,age);
            nm = inp.nextLine();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        String c = "Y";
        do
            {
            System.out.println("MENU:");
            System.out.println();
            System.out.println("    1. Add HealthCare Institute");
            System.out.println("    2. Remove Records of Admitted Patients");
            System.out.println("    3. Remove Records of Institutes that have Closed Admission");
            System.out.println("    4. Number of Patients in Waiting Camp");
            System.out.println("    5. Number of Institutes that are Open for Admission");
            System.out.println("    6. Display Institute Details");
            System.out.println("    7. Display Patient Details");
            System.out.println("    8. Display Details of All Patients");
            System.out.println("    9. Display Details of Patients admitted in an Institute");
            System.out.print("\n\nEnter Option: ");
            int op = inp.nextInt();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

            switch(op)
            {
                case 1:
                    String nm1 = inp.nextLine();
                    System.out.println("ENTER INSTITUTE DETAILS:");
                    System.out.println();
                    System.out.print("    Institute Name: ");
                    nm1 = inp.nextLine();
                    System.out.print("    Temperature Criteria (MAX): ");
                    float temp = inp.nextFloat();
                    System.out.print("    Oxygen Level Criteria (MIN): ");
                    int oxy = inp.nextInt();
                    System.out.print("    Available Beds: ");
                    int bedavail = inp.nextInt();
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    instituteobj.add(new Institute(nm1,temp,oxy,bedavail));
                    instituteobj.get(instituteobj.size()-1).display();
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    nm1 = inp.nextLine();

                    if(instituteobj.get(instituteobj.size()-1).admissionstatus==1)
                    {
                        for(int j = 0;j<nPatients && instituteobj.get(instituteobj.size()-1).bedavail!=0;j++)
                        {
                            if(patientsobj[j].assigned==0)
                            {
                                if(patientsobj[j].temp<=instituteobj.get(instituteobj.size()-1).tempcrite && patientsobj[j].oxy>=instituteobj.get(instituteobj.size()-1).oxycrite)
                                {
                                    patientsobj[j].assigned = 1;
                                    System.out.print("Recovery Days for Admitted Patient (ID: " + (j+1) + "): ");
                                    patientsobj[j].recdays = inp.nextInt();
                                    patientsobj[j].institutename = instituteobj.get(instituteobj.size()-1).name;
                                    instituteobj.get(instituteobj.size()-1).bedavail--;
                                    instituteobj.get(instituteobj.size()-1).checkStatus();
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    int del = 0;
                    for(int j=0;j<nPatients;j++)
                    {
                        if(patientsobj[j].assigned==1)
                        {
                            if(patientsobj[j].deleted==0)
                            {
                                del++;
                                System.out.println("Deleted Patient Record (ID: " + (j+1) + "): " + patientsobj[j].name);
                                patientsobj[j].deleted = 1;
                            }
                        }
                    }
                    if(del==0)
                    {
                        System.out.println("No Patient Record Deleted.");
                    }
                    break;
                case 3:
                    int del1 = 0;
                    for(int j=0;j<instituteobj.size();j++)
                    {
                        if(instituteobj.get(j).admissionstatus==0)
                        {
                            del1++;
                            System.out.println("Deleted Institute Record: " + instituteobj.get(j).name + " (" + instituteobj.get(j).beds + "/" + instituteobj.get(j).beds + " beds filled)");
                            instituteobj.get(j).deleted = 1;
                        }
                    }
                    if(del1==0)
                    {
                        System.out.println("No Institute Record Deleted.");
                    }
                    break;
                case 4:
                    int count = 0;
                    for(int j=0;j<nPatients;j++)
                    {
                        if(patientsobj[j].assigned==0)
                        {
                            count++;
                        }
                    }
                    System.out.println("Number of Patients still in the Waiting Camp: " + count);
                    break;
                case 5:
                    int count1 = 0;
                    for(int j=0;j<instituteobj.size();j++)
                    {
                        if(instituteobj.get(j).admissionstatus==1)
                        {
                            count1++;
                        }
                    }
                    System.out.println("Number of Institutes that are Open for Admission: " + count1);
                    break;
                case 6:
                    int found = 0;
                    System.out.print("Enter Institute Name: ");
                    String chk = inp.nextLine();
                    chk = inp.nextLine();
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for(int j=0;j<instituteobj.size();j++)
                    {
                        if(instituteobj.get(j).name.equals(chk))
                        {
                            if(instituteobj.get(j).deleted==0)
                            {
                                found++;
                                instituteobj.get(j).display();
                            }
                        }
                    }
                    if(found==0)
                    {
                        System.out.println("No such Institute Record found.");
                    }
                    break;
                case 7:
                    int found1 = 0;
                    System.out.print("Enter Patient ID: ");
                    int chk1 = inp.nextInt();
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for(int j=0;j<nPatients;j++)
                    {
                        if(patientsobj[j].id==chk1)
                        {
                            if(patientsobj[j].deleted==0)
                            {
                                found1++;
                                patientsobj[j].display();
                            }
                        }
                    }
                    if(found1==0)
                    {
                        System.out.println("No such Patient Record found.");
                    }
                    break;
                case 8:
                    int found2 = 0;
                    for(int j=0;j<nPatients;j++)
                    {
                        if(patientsobj[j].deleted==0)
                        {
                            found2++;
                            System.out.println(patientsobj[j].name + " (ID: " + patientsobj[j].id + ")");
                        }
                    }
                    if(found2==0)
                    {
                        System.out.println("No Patient Record found.");
                    }
                    break;
                case 9:
                    int found3 = 0;
                    int found4 = 0;
                    System.out.print("Enter Institute Name: ");
                    String chk3 = inp.nextLine();
                    chk3 = inp.nextLine();
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for(int j=0;j<instituteobj.size();j++)
                    {
                        if(instituteobj.get(j).name.equals(chk3))
                        {
                            if(instituteobj.get(j).deleted==0)
                            {
                                found3++;
                            }
                        }
                    }
                    for(int k=0;k<nPatients && found3!=0;k++)
                    {
                        if(patientsobj[k].deleted==0)
                        {
                            if(patientsobj[k].institutename.equals(chk3))
                            {
                                found4++;
                                System.out.println(patientsobj[k].name + " (ID: " + patientsobj[k].id + "), Recovery Time is " + patientsobj[k].recdays + " days.");
                            }
                        }
                        if(patientsobj[k].deleted==1)
                        {
                            if(patientsobj[k].institutename.equals(chk3))
                            {
                                found4++;
                                System.out.println(patientsobj[k].name + " (ID: " + patientsobj[k].id + "), Recovery Time is " + patientsobj[k].recdays + " days.   (Patient Record Deleted)");
                            }
                        }
                    }
                    if(found3==0)
                    {
                        System.out.println("No such Institute Record found.");
                    }
                    if(found3!=0 && found4==0)
                    {
                        System.out.println("No Patient Record found in the Institute.");
                    }
                    break;
            }

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("Go Back to MENU? (Y/N): ");
            c = inp.next();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        }while(c.equals("Y") || c.equals("y"));

        System.out.println("GOODBYE!");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }
}