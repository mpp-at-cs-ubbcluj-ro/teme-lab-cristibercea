package ubb.scs.mpp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ubb.scs.mpp.repository.ComputerRepairRequestRepository;
import ubb.scs.mpp.repository.ComputerRepairedFormRepository;
import ubb.scs.mpp.repository.jdbc.ComputerRepairRequestJdbcRepository;
import ubb.scs.mpp.repository.jdbc.ComputerRepairedFormJdbcRepository;
import ubb.scs.mpp.services.ComputerRepairServices;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class RepairShopConfig {
    @Bean
    Properties getProps() {
        Properties props = new Properties();
        try {
            System.out.println("Searching for bd.config in directory" + ((new File(".")).getAbsolutePath()));
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.err.println("Error! bd.config not found: " + e);
        }
        return props;
    }

    @Bean
    ComputerRepairRequestRepository requestsRepo(){
        return new ComputerRepairRequestJdbcRepository(getProps());
    }

    @Bean
    ComputerRepairedFormRepository formsRepo(){
        return new ComputerRepairedFormJdbcRepository(getProps());
    }

    @Bean
    ComputerRepairServices services(){
        return new ComputerRepairServices(requestsRepo(),formsRepo());
    }

}
