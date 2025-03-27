package ubb.scs.mpp.repository;

import ubb.scs.mpp.model.ComputerRepairedForm;

import java.util.List;

public interface ComputerRepairedFormRepository extends Repository<Integer, ComputerRepairedForm> {
    List<ComputerRepairedForm> filterByEmployee(String employee);
    List<ComputerRepairedForm> filterByEmployeeAndDate(String employee, String date);
}
