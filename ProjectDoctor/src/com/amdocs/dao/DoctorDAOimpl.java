package com.amdocs.dao;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.pojos.Doctor;
import com.amdocs.exception.*;
public class DoctorDAOimpl implements DoctorDAO{
	
	List<Doctor> doctorList=new ArrayList<>();

	@Override
	public int addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		doctorList.add(doctor);
		return doctor.getDoctorId();
	}

	@Override
	public int deleteDoctor(int doctorId) throws DoctorNotFoundException{
		// TODO Auto-generated method stub
		boolean y = true;
		Iterator<Doctor>itr = doctorList.iterator();
		while(itr.hasNext()) {
			if(itr.next().getDoctorId()==doctorId)
			{
				y=false;
				itr.remove();
			}
		}
		if(y)
			return 0;
		
		return doctorId;
	}

	@Override
	public boolean updateDoctorFees(int doctorId, double fees) throws DoctorNotFoundException{
		for(Doctor doctor:doctorList)
		{
			if(doctor.getDoctorId()==doctorId)
			{				
				//doctorList.remove(doctor);
				int indexof=doctorList.indexOf(doctor);
				doctor.setFees(fees);
				doctorList.set(indexof, doctor);
				return true;
			}			
		}
		return false;
	}

	@Override
	public List<Doctor> showAllDoctors() {
		return doctorList;
	}

	@Override
	public List<Doctor> searchBySpecialization(String specialization) throws DoctorNotFoundException{
		List<Doctor> doctortempList=new ArrayList<>();
		for(Doctor doctor:doctorList)
		{
			if(doctor.getSpecialization().equals(specialization))
			{				
				doctortempList.add(doctor);
			}			
		}
		return doctortempList;
	}

	@Override
	public List<Doctor> searchByFeesAndShift(String availableShift,double fees)throws DoctorNotFoundException {
		List<Doctor> doctortempList=new ArrayList<>();
		for(Doctor doctor:doctorList)
		{
			if(doctor.getAvailableShift().equals(availableShift) && doctor.getFees()<=fees)
			{				
				doctortempList.add(doctor);
			}			
		}
		return doctortempList;
	}

	@Override
	public int countDoctorsByShift(String availableShift) throws DoctorNotFoundException{
		List<Doctor> doctortempList=new ArrayList<>();
		for(Doctor doctor:doctorList)
		{
			if(doctor.getAvailableShift().equals(availableShift))
			{				
				doctortempList.add(doctor);
			}			
		}
		
		return doctortempList.size();
	}

}
