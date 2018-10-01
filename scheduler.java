
package org.cloudbus.cloudsim.examples;


import java.text.DecimalFormat;
import java.util.*;
import java.security.*;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

/**
 * A simple example showing how to create
 * a datacenter with one host and run 100
 * cloudlets on it. The cloudlets run in twenty VMs
 * with the same MIPS requirements.
 * The central scheduler is mapping the cloudlets into Vms.
 */




/**
 BEWARE OF SCHEDULER LIMITATION AS FAR AS NUMBER OF TASKS/MACHINES
 */
public class AgaCloudSimCentralScheduler {

    /**
     * The cloudlet list.
     */
    private static List<Cloudlet> cloudletList;

    /**
     * The vmlist.
     */
    private static List<Vm> vmlist;


    public static void main(String[] args) {

        Log.printLine("Starting Calculations");

        try {
            // First step: Initialize the CloudSim package. It should be called
            // before creating any entities.
            int num_user = 1;   // number of cloud users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;  // mean trace events

            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);

            // Second step: Create Datacenters
            //Datacenters are the resource providers in CloudSim. We need at list one of them to run a CloudSim simulation
            @SuppressWarnings("unused")
            Datacenter datacenter0 = createDatacenter("Datacenter_0");

            //Third step: Create Broker
            DatacenterBroker broker = createBroker();
            int brokerId = broker.getId();

            //Fourth step: Create one virtual machine
            vmlist = new ArrayList<Vm>();

            //VM description, 4 MV types



            //VM type one
            int vmid1 = 0;
            int mips1 = 710;//computing capacity maximum
            long size1 = 200; //image size (MB)
            int ram1 = 512;//1600; //vm memory (MB)
            long bw1 = 10;
            int pesNumber1 = 1; //number of cpus
            String vmm1 = "Xen"; //VMM name
             long cc1=710;//computing capacity in MIPS

            //VM type 2
            int vmid2 = 1;
            int mips2 = 1430;//computing capacity maximum
            long size2 = 200; //image size (MB)
            int ram2 = 512;//3750; //vm memory (MB)
            long bw2 = 10;
            int pesNumber2 = 1; //number of cpus
            String vmm2 = "Xen"; //VMM name
            long cc2=1430;//computing capacity in MIPS


            //VM type 3
            int vmid3 = 2;
            int mips3 = 2480;//computing capacity maximum
            long size3 = 200; //image size (MB)
            int ram3 = 512;//7300; //vm memory (MB)
            long bw3 = 10;
            int pesNumber3 = 2; //number of cpus
            String vmm3 = "Xen"; //VMM name
            long cc3=2480;//computing capacity in MIPS


            //VM type 4
            int vmid4 = 3;
            int mips4 = 2380;//computing capacity maximum
            long size4 = 200; //image size (MB)
            int ram4 = 512;//30000; //vm memory (MB)
            long bw4 = 10;
            int pesNumber4 = 8; //number of cpus
            String vmm4 = "Xen"; //VMM name
            long cc4=2380;//computing capacity in MIPS


           int VM_number=16; //16 maszyn po 4 z kazdego typu
            int Cloudlet_number=16*20;//po 20 tasków na maszyne srednio tj 230 tasków



            long[] capacityvector = new long[VM_number];
            for (int i = 0; i < 20; i++) {
                    if (i<4)
                capacityvector[i]=cc1;
                        else
                            if (i<8)
                            capacityvector[i]=cc2;
                            else
                            if (i<12)
                                capacityvector[i]=cc3;
                            else if (i<16)
                                capacityvector[i]=cc4;

            }
            int vmid = 0;

            //for (int i = 0; i < 5; i++) {

//                Random randomobject = new Random();
  //              capacityvector[i] = (long) randomobject.nextInt(mips);//nextInt(mips) losuje liczbe z zakresu 0...mips-1
    //        }
            //create  VMs  of type 1

            for (int i = 0; i < 4; i++) {
                vmid = i;

               // Vm m = new Vm(vmid, brokerId, capacityvector[i], pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
        Vm m = new Vm(vmid, brokerId, capacityvector[i], pesNumber1, ram1, bw1, size1, vmm1, new CloudletSchedulerTimeShared());

                //add the VMs to the vmList
                vmlist.add(m);
            }

            //create  VMs  of type 2
            for (int i = 4; i < 8; i++) {
                vmid = i;

                // Vm m = new Vm(vmid, brokerId, capacityvector[i], pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
                Vm m = new Vm(vmid, brokerId, capacityvector[i], pesNumber2, ram2, bw2, size2, vmm2, new CloudletSchedulerTimeShared());

                //add the VMs to the vmList
                vmlist.add(m);
            }
            //create  VMs  of type 3
            for (int i = 8; i < 12; i++) {
                vmid = i;

                // Vm m = new Vm(vmid, brokerId, capacityvector[i], pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
                Vm m = new Vm(vmid, brokerId, capacityvector[i], pesNumber3, ram3, bw3, size3, vmm3, new CloudletSchedulerTimeShared());

                //add the VMs to the vmList
                vmlist.add(m);
            }
            //create  VMs  of type 4
            for (int i = 12; i < VM_number; i++) {
                vmid = i;

                // Vm m = new Vm(vmid, brokerId, capacityvector[i], pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
                Vm m = new Vm(vmid, brokerId, capacityvector[i], pesNumber4, ram4, bw4, size4, vmm4, new CloudletSchedulerTimeShared());

                //add the VMs to the vmList
                vmlist.add(m);
            }


            //submit vm list to the broker
            broker.submitVmList(vmlist);


            //Fifth step: Create  Cloudlets
            cloudletList = new ArrayList<Cloudlet>();

            //Cloudlet properties
            int id = 0;
            int pesNumber = 1;
            long length = 250000;//workload im mips (maximum)
            long fileSize = 300;
            long outputSize = 300;
            UtilizationModel utilizationModel = new UtilizationModelFull();
            long[] workloadvector = new long[Cloudlet_number];
            for (int i = 0; i < Cloudlet_number; i++) {

                Random randomobject = new Random();

                workloadvector[i] = (long) randomobject.nextInt((int) length);//nextInt(lenhth) losuje liczbe z zakresu lenght-1
            }

//tworzymy taski
            for (int i = 0; i < Cloudlet_number; i++) {
                id = i;

                Cloudlet task = new Cloudlet(id, workloadvector[i], pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
                task.setUserId(brokerId);
                cloudletList.add(task);
            }

            // Cloudlet            task=cloudletList.get(1);


            //submit cloudlet list to the broker


            //SCHEDULE CALCULATION the permutation with repetitions representation, these are ids of vms calculation the consecutive tasks


            long[] schedule = new long[workloadvector.length];
            //równy rozdzial
           // for (int i = 0; i < workloadvector.length; i++) {
              //  schedule[i] = (long) i % capacityvector.length;
            //}


            scheduler(workloadvector, capacityvector, schedule);//JEST NAPISANY DLA 5 MASZYN I 10 TASKÓW TRZEBA GO PRZEROBIC NA BARDZIEJ OGÓLNY
            // schedule to bedzie lista maszyn wykonujacych kolejne taski np. dla 10 tasków i 2 vm:
            //workload vector=[10 11 , 12 43 , 44 85, 86 78 , 68 96] to nak³ad na kolejne taski
            //schedule     =  [10 11, 44  85 , 44 85, 86 78 , 68 96] to kolejne workloady do zabrania przez nastepujêce po sobie maszyny wirtualne
            // dla maszyn o numerach =[0 1   , 2 3   , 4 5  , 6 7   , 8 9]

            //bind the cloudlets to the vms. This way, the broker
            // will submit the bound cloudlets only to the specific VM

            for  (int j = 0; j < Cloudlet_number; j++)
                System.out.println("DRUKUJE SCHEDULE " + schedule[j]);

           int[] VMidsfromschedule = new int[Cloudlet_number];
            //przeliczenie schedule na cloudlet ID
             for (int i = 0; i < Cloudlet_number; i++) {
             for  (int j = 0; j < Cloudlet_number; j++)
                 if (schedule[i]  ==  workloadvector[j])  VMidsfromschedule[j]=i;



             }

            broker.submitCloudletList(cloudletList);

          //tu jest potrzeba lepsza petla //JEST NAPISANA DLA 5 MASZYN I 10 TASKÓW TRZEBA GO PRZEROBIC NA BARDZIEJ OGÓLNA
            broker.bindCloudletToVm( VMidsfromschedule[0],0 ) ;
            broker.bindCloudletToVm( VMidsfromschedule[1],0 ) ;
            broker.bindCloudletToVm( VMidsfromschedule[2],1 ) ;
            broker.bindCloudletToVm( VMidsfromschedule[3],1 ) ;
            broker.bindCloudletToVm( VMidsfromschedule[4],2 ) ;
            broker.bindCloudletToVm( VMidsfromschedule[5],2 ) ;
            broker.bindCloudletToVm( VMidsfromschedule[6],3 ) ;
            broker.bindCloudletToVm( VMidsfromschedule[7],3 ) ;
            broker.bindCloudletToVm( VMidsfromschedule[8],4 ) ;
            broker.bindCloudletToVm( VMidsfromschedule[9],4 ) ;




                      // bindCloudletToVm(cloudlet ID, VM ID )


            // Sixth step: Starts the simulation
            CloudSim.startSimulation();


            // Final step: Print results when simulation is over
            List<Cloudlet> newList = broker.getCloudletReceivedList();

            CloudSim.stopSimulation();

            printCloudletList(newList);

            Log.printLine("Simulation finished");
        } catch (Exception e) {
            e.printStackTrace();
            Log.printLine("The simulation has been terminated due to an unexpected error");
        }
    }

    private static Datacenter createDatacenter(String name) {

        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store
        //    our machine
        List<Host> hostList = new ArrayList<Host>();

        // 2. A Machine contains one or more PEs or CPUs/Cores.
        // In this example, it will have only one core.
        List<Pe> peList = new ArrayList<Pe>();

        int mips = 1000000000;

        // 3. Create PEs and add these into a list.
        peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

        //4. Create Host with its id and list of PEs and add them to the list of machines
        int hostId = 0;
        int ram = 2048 ; //host memory (MB)
        long storage = 1000000000; //host storage
        int bw = 1000000000;

        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList,
                        new VmSchedulerTimeShared(peList)
                )
        ); // This is our machine


        // 5. Create a DatacenterCharacteristics object that stores the
        //    properties of a data center: architecture, OS, list of
        //    Machines, allocation policy: time- or space-shared, time zone
        //    and its price (G$/Pe time unit).
        String arch = "x86";      // system architecture
        String os = "Linux";          // operating system
        String vmm = "Xen";
        double time_zone = 0.0;         // time zone this resource located
        double cost = 12.0;              // the cost of using processing in this resource, per sec
        double costPerMem = 0.0;        // the cost of using memory in this resource
        double costPerStorage = 0.0;    // the cost of using of storage in this resource
        double costPerBw = 0.0;            // the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>();    //we are not adding SAN devices by now

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);


        // 6. Finally, we need to create a PowerDatacenter object.
        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }

    //We strongly encourage users to develop their own broker policies, to submit vms and cloudlets according
    //to the specific rules of the simulated scenario
    private static DatacenterBroker createBroker() {

        DatacenterBroker broker = null;
        try {
            broker = new DatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }

    /**
     * Prints the Cloudlet objects
     *
     * @param list list of Cloudlets
     */
    private static void printCloudletList(List<Cloudlet> list) {
        int size = list.size();
        Cloudlet cloudlet;

        String indent = "    ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
                "Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" + indent + "Finish Time" + indent + "COST" + indent);

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            cloudlet = list.get(i);
            Log.print(indent + cloudlet.getCloudletId() + indent + indent);

            if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
                Log.print("SUCCESS");

                Log.printLine(indent + indent + cloudlet.getResourceId() + indent + indent + indent + cloudlet.getVmId() +
                        indent + indent + dft.format(cloudlet.getActualCPUTime()) + indent + indent + dft.format(cloudlet.getExecStartTime()) +
                        indent + indent + dft.format(cloudlet.getFinishTime()) + indent + indent + cloudlet.getCostPerSec() * cloudlet.getActualCPUTime());


            }
        }

    }

    static void scheduler(long[] workloadvector, long[] capacityvector, long[] schedule) {
        int tasknb = workloadvector.length; //workloadvector to wektor WL kolejnych tasków - ile jest tasków np.100
        int vmnb = capacityvector.length; //capacityvector to wektor cc dla kolejnych VM - ile jest maszyn np. 5



        // schedule to bedzie lista maszyn wykonujacych kolejne taski np. dla 10 tasków i 2 vm:
        //workload vector=[10 11 , 12 43 , 44 85, 86 78 , 68 96] to nak³ad na kolejne taski
        //schedule     =  [10 11, 44  85 , 44 85, 86 78 , 68 96] to kolejne workloady do zabrania przez nastepujêce po sobie maszyny wirtualne
        // dla maszyn o numerach =[0 1   , 2 3   , 4 5  , 6 7   , 8 9]
        Random randomobject = new Random();
        SecureRandom binaryrandomobject = new SecureRandom();
        //long taskzero = (long) randomobject.nextInt( tasknb);//nextInt(lenhth) losuje liczbe z zakresu 0..lenght-1

        List<Long> chromosomes = new ArrayList<Long>();

//losowe mieszanie populacji poczatkowej

        shuffleArray(workloadvector);//Fisher–Yates shuffle array function
//przepisaywanie wektora workload na chromosom

        for (int i = 0; i < tasknb; i++) {
            chromosomes.add(workloadvector[i]);
            System.out.println("workloadvectoro numerze  " +i+ "to" +workloadvector[i]);
        }
        for (int i = 0; i < vmnb; i++) {
            System.out.println("capacityvector o numerze  " +i+ "to" +capacityvector[i]);
        }

        System.out.println("d³ugosc wekrora tasków " + tasknb);
        System.out.println("d³ugosc chromosomu " + chromosomes.size());
        //rozdzia³ na geny - schedule jest uk³adany, tak aby kazda maszyna mia³a tyle samo taskow
        int genelenght = (int) tasknb / vmnb;//ilosc tasków na maszyne -ilosc elementów w genie
        System.out.println("ilosc tasków na maszyne -ilosc elementów w genie " + genelenght);

        int genenumber = vmnb; //ilosc genów to ilosc maszyn

        System.out.println("ilosc genów równa ilosci maszyn to " + genenumber);




        //dzielimy chromosom na geny chromosom
        //dla 10 tsków i 5 maszyn, wektor podzia³ów wyglada tak
       //          workload vector=[10       11           , 12         43 ,           44          85,            86          78 ,            68             96]=chromosom
        // maszyna odpowiedzialna   VM1       VM1         ,VM2          VM2,          VM3         VM3,           VM4         VM4,            VM5            VM5
                  //cutpoint       =[                   cutpoint[0]               cutpoint[1]               cutpoint[2]                  cutpoint[3]              cutpoint[4]]
                  //cutpoint       =[                   2                               4                          6                           8                        10]
        //oddziela od siebie taski kolejnych maszyn w wektorze chromosomu, wskazuje nr komórki w chromosomie, gdzie zaczyna siê kolejny gen
        //chromosomes=[gene0 gene1 gene2 gene3 gene4 ]

        List<Long> gene = new ArrayList<Long>();
        int[] cutpoint = new int[vmnb+1];
        cutpoint[0] = (int) tasknb / vmnb ;
        System.out.println("cutpoint nr zero  znajduje sie w punkcie "+cutpoint[0]);
        for (int i = 1; i < genenumber-1; i++) {
            cutpoint[i] = cutpoint[i - 1] + (int) tasknb / vmnb ;

            System.out.println("cutpoint nr " + i+"znajduje sie w punkcie "+cutpoint[i]);
        }
        cutpoint[genenumber-1] = cutpoint[genenumber-2]+(int) tasknb / vmnb;
        System.out.println("cutpoint ostatni, tj numer"+ genenumber+ "minus 1" + " znajduje sie w punkcie "+ cutpoint[genenumber-1]);

        double makespan=0.0;
        for (int i = 0; i < genenumber; i++) {
            double temp=0;


            int poczatekgenu;
            poczatekgenu=cutpoint[i] - genelenght ;
            int koniecgenu;
            koniecgenu=cutpoint[i]-1;
            System.out.println("gen nr " + i+"zaczyna sie w punkcie "+ poczatekgenu+ "a konczy sie w punkcie "+ koniecgenu);

            //Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
            gene = chromosomes.subList(cutpoint[i] - genelenght , cutpoint[i]);
            System.out.println("gen nr " + i+"ma d³ugosc "+ gene.size());


            for (int j = 0; j < genelenght; j++) {

                System.out.println("ECT(i,j) dla maszyny numer " + i+"oraz tasku w genie  "+j+ "to iloraz"+ (double) gene.get(j)+"przez" +capacityvector[i]   );
                temp = temp + (double) gene.get(j) / capacityvector[i];
            }
            makespan=makespan+ temp;


        }

        System.out.println("Makespan dla pierwszego chromosomu wynosi " + makespan);

        for (int licznikepok = 1; licznikepok < 500; licznikepok++) {
            //--------------------tutaj zaczyna sie przetwarzanie chromosomu ----------------------------
            System.out.println("Epoka nr " + licznikepok);

            //wyznaczamy czas wykonania zadañ w oparciu o ETC dla poszczególnych genów


            double[] timegene = new double[vmnb];

            for (int i = 0; i < vmnb; i++)
                timegene[i] = 0.0;


            for (int i = 0; i < vmnb; i++) {
                double temp = 0.0;

                gene = chromosomes.subList(cutpoint[i] - genelenght , cutpoint[i]);
                for (int j = 0; j < genelenght - 1; j++) {


                    temp = temp + (double) gene.get(j) / capacityvector[i];
                }
                timegene[i] = temp;
                System.out.println("time for gene " + i + "equals" + timegene[i]);

            }
            int worstparrent = 0;
            int bestparrent = 0;

            int liczbakrzyzowan;
            liczbakrzyzowan=(int)vmnb/2;
            System.out.println("liczba krzyzowañ bz zamraznia  "+liczbakrzyzowan );
            List<Integer> matedgenes = new ArrayList<Integer>();
//--------------------tutaj zaczyna sie petle krzyzowania ----------------------------------
            for (int licznikkrzyzowan = 0; licznikkrzyzowan < liczbakrzyzowan; licznikkrzyzowan++)

            {

                System.out.println("krzyzowanie  nr " + licznikkrzyzowan);
//wybór genów do krzyzowania
                //wybór najgorszego rodzica tj genu dla którego mamy najkrotszy czas wykonania zadan
                double min = Double.MAX_VALUE;

                for (int i = 0; i < genenumber; i++) {
                    System.out.println("badamy gen numer " + i);
                    //ale sposród tych, których jeszcze nie rozmnazalismy
                    //boolean	contains(Object o)
                    //Returns true if this list contains the specified element.
                    if (!matedgenes.contains(i)) {// najgorszy jest poszukiwany tylko na liscie tych,
                        // które sie jeszcze nie rozmnaza³y
                        System.out.println("ten gen sie jeszcze nie rozmnazal " );
                        if (timegene[i] < min) {
                            worstparrent = i;
                            min = timegene[i];
                        }
                    }
                    else  System.out.println("ten gen sie juz rozmnazal " );
            }

                System.out.println("najgorszy " + worstparrent);
                matedgenes.add(worstparrent);//zaznaczamy, który gen juz by³ krzyzowany

                //wybór najlepszego rodzica tj genu dla którego mamy najd³uzszy czas wykonania zadan

                double max = Double.MIN_VALUE;

                for (int j = 0; j < genenumber; j++) {

                    if (!matedgenes.contains(j)) {// najlepszy jest poszukiwany tylko na liscie tych,
                        // które sie jeszcze nie rozmnaza³y
                        if (timegene[j] > max) {
                            bestparrent = j;
                            max = timegene[j];
                        }
                    }
                }

                System.out.println("najlepszy " + bestparrent);

                matedgenes.add(bestparrent);//zaznaczamy, który gen juz by³ krzyzowany}

                //no to krzyzujemy, wymienaj¹c geny losowo

                byte exchangetable[] = new byte[genelenght];
                binaryrandomobject.nextBytes(exchangetable);
                exchangetable[0]=1;
                exchangetable[genelenght-1]=1;

                //rodzice
                //gene = chromosomes.subList(cutpoint[bestparrent] - genelenght , cutpoint[bestparrent]);
                System.out.println("pierwszym rodzicem jest  nr " + bestparrent  +"o dlugosci  " + chromosomes.subList(cutpoint[bestparrent] - genelenght , cutpoint[bestparrent]).size());
                System.out.println("jego pierwszy task to" +  chromosomes.subList(cutpoint[bestparrent] - genelenght, cutpoint[bestparrent]).get(0));
                System.out.println("jego drugi task to" +  chromosomes.subList(cutpoint[bestparrent] - genelenght , cutpoint[bestparrent]).get(1));
                //gene = chromosomes.subList(cutpoint[worstparrent] - genelenght, cutpoint[worstparrent]);
                System.out.println("drugim rodzicem jest  nr " + worstparrent  +"o dlugosci  " + chromosomes.subList(cutpoint[worstparrent] - genelenght , cutpoint[worstparrent]).size());
                System.out.println("jego pierwszy task to" +  chromosomes.subList(cutpoint[worstparrent] - genelenght, cutpoint[worstparrent]).get(0));
                System.out.println("jego drugi task to" +  chromosomes.subList(cutpoint[worstparrent] - genelenght , cutpoint[worstparrent]).get(1));




                for ( int i = 0;   i < genelenght; i++) {
                    if (exchangetable[i] == 1)

                    {
                        System.out.println("Wymieniaamy w task nr " + i);

                        List<Long> bufor = new ArrayList<Long>();
                        bufor.add(chromosomes.subList(cutpoint[bestparrent] - genelenght, cutpoint[bestparrent]).get(i));
                        bufor.add(chromosomes.subList(cutpoint[worstparrent] - genelenght, cutpoint[worstparrent]).get(i));

                        chromosomes.subList(cutpoint[worstparrent] - genelenght, cutpoint[worstparrent]).set(i, bufor.get(0));


                        chromosomes.subList(cutpoint[bestparrent] - genelenght, cutpoint[bestparrent]).set(i, bufor.get(1));
                    }

                }

                        // to pokrzyzowalismy, teraz wybieramy nastêpnych rodziców

                        System.out.println(" Po wymianie pierwszy task rodzica"+bestparrent +"to   " +  chromosomes.subList(cutpoint[bestparrent] - genelenght, cutpoint[bestparrent]).get(0));
                        System.out.println("Po wymianie drugi task rodzica"+bestparrent+ "to   " +  chromosomes.subList(cutpoint[bestparrent] - genelenght , cutpoint[bestparrent]).get(1));


                        System.out.println(" Po wymianie pierwszy task rodzica"+ worstparrent+"to   " +  chromosomes.subList(cutpoint[worstparrent] - genelenght, cutpoint[worstparrent]).get(0));
                        System.out.println(" Po wymianie drugi task rodzica"+ worstparrent+"to   " +  +  chromosomes.subList(cutpoint[worstparrent] - genelenght , cutpoint[worstparrent]).get(1));





            }
            //
            // --------------------tutaj konczy sie petle krzyzowania ----------------------------------

            double currentmakespan=0;

            for (int i = 0; i < vmnb; i++) {
                double tempp=0;

                gene = chromosomes.subList(cutpoint[i] - genelenght + 1, cutpoint[i]);
                for (int j = 0; j < genelenght-1 ; j++) {


                    tempp = tempp + (double) gene.get(j) / capacityvector[i];
                }
                currentmakespan=currentmakespan+ tempp;


            }

            System.out.println("Makespan   chromosomu w iteracji nr " + licznikepok  +"wynosi " + currentmakespan);

        }//--------------------tutaj konczya sie przetwarzanie chromosomu ----------------------------------

        for (int i = 0; i < tasknb; i++) {
           schedule[i]= chromosomes.get(i);}

    }





    private static void shuffleArray(long[] array) {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }
}








