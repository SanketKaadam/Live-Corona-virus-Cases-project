package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class App
{
    public static String getData(String c) throws Exception {

        StringBuffer br = new StringBuffer();
        br.append("<html>"+ // we html bcoz we are using html tags in code
                "<body style='text-align:center;color:red;'>"); // we use body bcoz we want to give design to our text
        br.append(c.toUpperCase()+"<br>"); // it shows county name on label we use <br> to make new line
        String url = "https://www.worldometers.info/coronavirus/country/"+c+"/";
        Document doc = Jsoup.connect(url).get();

        // #maincounter-wrap
        Elements element = doc.select("#maincounter-wrap");
        element.forEach((e)->{
            String text = e.select("h1").text();
            String count = e.select(".maincounter-number>span").text();
            br.append(text).append(count).append("<br>");
        });

        br.append("</body>"
                +"</html>");
        return br.toString();
    }

    public static void main( String[] args ) throws Exception {

     /*   System.out.println( "Hello World!" );
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter country name");
        String co = sc.nextLine();
        System.out.println(getData(co)); */

        // Creating GUI
        JFrame root = new JFrame("Details of Country"); // main window
        root.setSize(500,500); // setting window dimension
        Font f = new Font("Poppins",Font.BOLD,30); // font name, style , size
        // Text field
        JTextField field = new JTextField();
        // label
        JLabel dataL = new JLabel();
        field.setFont(f); // setting font for fields
        dataL.setFont(f); // setting font for labels
        dataL.setHorizontalAlignment(SwingConstants.CENTER); // here we set our all label data into center
        field.setHorizontalAlignment(SwingConstants.CENTER); // This allows us to write text in center
        JButton button = new JButton("Get");
        button.setFont(f); // setting font for button
        button.addActionListener(event->{
            // Click:
            try{
                String mainData= field.getText(); // here we use field bcoz we are fetching data from filed
                String result = getData(mainData); // here we pss mainData into getData() method
                dataL.setText(result); // setting result on label
            }catch (Exception e){
                e.printStackTrace();
            }

        }); // here we are adding listener do that when we click on button data will be fetched
        root.setLayout(new BorderLayout());
        root.add(field, BorderLayout.NORTH);// here we are adding over field on top of window, NORTH means top
        root.add(dataL, BorderLayout.CENTER); // here we put over label into center of window
        root.add(button, BorderLayout.SOUTH); // here we are adding over button on bottom of window, SOUTH means bottom
        root.setVisible(true); // to visible our window we use setVisible with true value
    }
}
