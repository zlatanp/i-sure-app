package com.ftn.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import com.ftn.service.implementation.RuleServiceImpl;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.model.dto.FileDTO;

/**
 * Created by zlatan on 11/25/17.
 */
@RestController
public class RuleController {

    private final RuleServiceImpl ruleService;

    @Autowired
    public RuleController(RuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

//    @RequestMapping(value = "/ruleTest", method = RequestMethod.GET, produces = "application/json")
//    public User getQuestions(@RequestParam(required = true) String name, @RequestParam(required = true) int number) {
//        User user = new User(name, number);
//        System.out.println("User request received for: " + user);
//        User user2 = ruleService.getUser(user);
//
//        return user2;
//    }

    @RequestMapping(value = "/openFile", method = RequestMethod.GET)
    public ResponseEntity<FileDTO> openFile() throws IOException {
        File file = new File("C:\\Users\\Jasmina\\Documents\\hg.repositories\\sep\\drools-spring-kjar\\src\\main\\resources\\com\\ftn\\isureprices\\rules\\ISureRuleService.drl");
        String content = readRuleFile(file);
        FileDTO fileDTO = new FileDTO(content);
        return new ResponseEntity(fileDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public void saveFile(@RequestBody FileDTO fileDTO) throws MavenInvocationException {
    	String tekst = fileDTO.getContent();
        System.out.println(tekst);
        File file = new File("C:\\Users\\Jasmina\\Documents\\hg.repositories\\sep\\drools-spring-kjar\\src\\main\\resources\\com\\ftn\\isureprices\\rules\\ISureRuleService.drl");
        saveRuleFile(tekst, file);

        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile( new File( "C:\\Users\\Jasmina\\Documents\\hg.repositories\\sep\\drools-spring-kjar\\pom.xml" ) );
        request.setGoals( Arrays.asList( "clean", "install" ) );

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(System.getenv("M2_HOME")));
        invoker.execute( request );
    }

    private void saveRuleFile(String text, File file) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
    }

    private String readRuleFile(File file) throws IOException {
        String lines = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            lines = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return lines;
    }
}