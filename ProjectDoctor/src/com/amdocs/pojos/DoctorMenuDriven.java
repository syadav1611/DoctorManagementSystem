package com.amdocs.pojos;
import java.util.List;
import java.util.Scanner;

import com.amdocs.dao.DoctorDAO;
import com.amdocs.dao.DoctorDAOimpl;
import com.amdocs.pojos.Doctor;
import com.amdocs.exception.*;
public class DoctorMenuDriven {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		DoctorDAO dao=new DoctorDAOimpl(); 
		do {
			
			System.out.println("1. Add new doctor\r\n"
					+ "2. Update doctor fees\r\n"
					+ "3. Delete doctor\r\n"
					+ "4. View all doctors\r\n"
					+ "5. Find doctor by specialization\r\n"
					+ "6. Find doctors whose fees is less than all doctors of given shift\r\n"
					+ "7. Count number of doctors by shift\r\n"
					+ "8. Exit\r\n"
					+ "");
			
			System.out.println("Enter your choice:");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				
				System.out.println("Enter Doctor Id");
				int doctorId=sc.nextInt();
				System.out.println("Enter doctorName");
				String doctorName=sc.next();
				System.out.println("Enter mobileNumber");
				String mobileNumber=sc.next();
				System.out.println("Enter specialization");
				String specialization=sc.next();
				System.out.println("Enter availableShift");
				String availableShift=sc.next();
				System.out.println("Enter fees");
				double fees=sc.nextDouble();
				Doctor doctor=new Doctor(doctorId,doctorName,mobileNumber,specialization,availableShift,fees);
				int y = dao.addDoctor(doctor);
				System.out.println(y);
				System.out.println("Doctor Added Successfully");
				
				
				break;
				
			case 2:
				System.out.println("Update doctor fees");
				System.out.println("Enter DoctorID");
				 int doctorId2=sc.nextInt();
				 System.out.println("Enter fees");
				 double fees2=sc.nextDouble();
				 boolean flag=false;
				try {
					flag = dao.updateDoctorFees(doctorId2, fees2);
					if(flag)
						 System.out.println("Doctor fees updated");
					 else
						 throw new DoctorNotFoundException("Doctor Not Found");
				} catch (DoctorNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 
				break;
				
			case 3:
				System.out.println("Enter the doctor id you want to delete");
				int iddelete = sc.nextInt();
				try 
				{
					int x = dao.deleteDoctor(iddelete);
					if(x!=0)
					{
						System.out.println("Doctor has been deleted");
					}
					else 
						throw new DoctorNotFoundException("Doctor not found");
				} 
				catch (DoctorNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("The doctor has been deleted");
				break;
				
			case 4:
				System.out.println("View All doctors");
				List<Doctor> doctorList=dao.showAllDoctors();
				for(Doctor doc:doctorList)
				{
					System.out.println(doc);
				}
				break;
				
			case 5:
				System.out.println("Enter the Specialization for which you want the doctor");
				String sp = sc.next();
				try 
				{
					List<Doctor> DoctorTemp=dao.searchBySpecialization(sp);
					if(DoctorTemp.size()>0)
					{
						for(Doctor doc:DoctorTemp)
						{
							System.out.println(doc);
						}
						System.out.println("Above is the list");
					}
					else
						throw new DoctorNotFoundException("No Doctor found");
				} 
				catch (DoctorNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:
				System.out.println("Enter the shift you want a doctor");
				String shift = sc.next();
				System.out.println("Enter your fees range");
				double fee = sc.nextInt();
				try 
				{
					List<Doctor> DoctorTemp=dao.searchByFeesAndShift(shift, fee);
					if(DoctorTemp.size()>0)
					{
						for(Doctor doc:DoctorTemp)
						{
							System.out.println(doc);
						}
						System.out.println("Above is the list");
					}
					else
						throw new DoctorNotFoundException("No doctor found in that shift ");
				} 
				catch (DoctorNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 7:
				System.out.println("Enter the shift you want the count of doctors");
				String shift1 = sc.next();
				int docCount=0;
				try 
				{
					docCount = dao.countDoctorsByShift(shift1);
					System.out.println("The number of doctors currently available are :"+docCount);
				} 
				catch (DoctorNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("The number of doctors currently available are :"+docCount);
				
			}
			
		}
		while(true);

	}

}
