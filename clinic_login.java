import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class db{
	public static Connection con;
	static{
		try{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
		}
		catch(Exception e){
			System.out.println("Exception"+e);
		}
	}
}

class mainframe extends Frame implements ActionListener
{
	Label l1;
	Button b1; Button b2;
	Image image;
	mainframe()
	{
		setLayout(null);
		Font f1=new Font("Elephant",Font.BOLD,26);
		Font f2=new Font("Elephant",Font.PLAIN,20);
		l1=new Label("Clinic Management System");
		l1.setBounds(500,50,400,90);
		l1.setFont(f1);
		add(l1);
		
		image = Toolkit.getDefaultToolkit().getImage("./clinic_images/clinic_management.jpg");
		
		b1=new Button("Start");
		b1.setBounds(470,470,150,100);
		b1.setFont(f2);
		b1.addActionListener(this);
		add(b1);
		
		b2=new Button("Close");
		b2.setBounds(690,470,150,100);
		b2.setFont(f2);
		b2.addActionListener(this);
		add(b2);
	}
	public void paint(Graphics g){
		g.drawImage(image,440,150,430,300,this); // x,y,getWidth(),getHeight()
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent ae)
	{if(ae.getSource()==b1){
		logintype lgt1=new logintype();
		lgt1.setSize(1370,730);
		lgt1.setBackground(Color.LIGHT_GRAY);
		lgt1.setVisible(true);
	}
	if(ae.getSource()==b2){
		dispose();
	}
}}

class logintype extends Frame implements ActionListener
{
	Label l1;
	Button b1; Button b2; Button b3;
	Image image;
	logintype()
	{
		setLayout(null);
		Font f1=new Font("Elephant",Font.BOLD,26);
		Font f2=new Font("Elephant",Font.PLAIN,20);
		l1=new Label("Login as...");
		l1.setBounds(450,50,400,90);
		l1.setFont(f1);
		add(l1);
		
		image = Toolkit.getDefaultToolkit().getImage("./clinic_images/login.jpeg");
		
		b1=new Button("Admin");
		b1.setBounds(480,470,150,100);
		b1.setFont(f2);
		b1.addActionListener(this);
		add(b1);
		
		b2=new Button("Manager");
		b2.setBounds(700,470,150,100);
		b2.setFont(f2);
		b2.addActionListener(this);
		add(b2);
		
		b3=new Button("Back");
		b3.setBounds(600,600,100,40);
		b3.setFont(f2);
		b3.addActionListener(this);
		add(b3);
	}
	
	public void paint(Graphics g){
		g.drawImage(image,450,150,430,300,this); // x,y,getWidth(),getHeight()
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1){
			admin_login alg1=new admin_login();
			alg1.setSize(1370,730);
			alg1.setBackground(Color.LIGHT_GRAY);
			alg1.setVisible(true);
		}
		if(ae.getSource()==b2){
			login lg1=new login();
			lg1.setSize(1370,730);
			lg1.setBackground(Color.LIGHT_GRAY);
			lg1.setVisible(true);
		}
		if(ae.getSource()==b3){
			dispose();
		}
	}
}

class admin_login extends Frame implements ActionListener
{
	Label l1;Label l2;Label l3;
	TextField t2; TextField t3;
	Button b1; Button b2;
	Image image;
	admin_login()
	{
		setLayout(null);
		Font f1=new Font("Elephant",Font.BOLD,26);
		Font f2=new Font("Elephant",Font.PLAIN,20);
		l1=new Label("Administrator Login");
		l1.setBounds(530,50,300,90);
		l1.setFont(f1);
		
		image = Toolkit.getDefaultToolkit().getImage("./clinic_images/admin_login.jpg");
		
		l2=new Label("Username:");
		l2.setBounds(450,470,100,30);
		l2.setFont(f2);
		t2=new TextField();
		t2.setBounds(650,470,200,30);
		t2.setFont(f2);
		
		l3=new Label("Password:");
		l3.setBounds(450,520,100,30);
		l3.setFont(f2);
		t3=new TextField();
		t3.setBounds(650,520,200,30);
		t3.setFont(f2);
		
		b1=new Button("Login");
		b1.setBounds(500,600,100,40);
		b1.setFont(f2);
		b1.addActionListener(this);
		
		b2=new Button("Back");
		b2.setBounds(650,600,100,40);
		b2.setFont(f2);
		b2.addActionListener(this);
		
		add(l1);add(l2);add(l3);
		add(t2);add(t3);
		add(b1); add(b2);
	}
	
	public void paint(Graphics g){
		g.drawImage(image,450,150,430,300,this); // x,y,getWidth(),getHeight()
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent ae)
	{if(ae.getSource()==b1){
		try 
			{
				//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
				Statement st=db.con.createStatement();
				String s1, s2, s3, s4;
				int f=0;
				//st.executeUpdate("delete from teacher where Tech_ID=300");
				
				ResultSet rs=st.executeQuery("select * from admin_login");
				s3=t2.getText();
				s4=t3.getText();
					while(rs.next())
					{
						s1=rs.getString("Username");
						s2=rs.getString("Password");
						if(s1.equals(s3) && s2.equals(s4)){
							f=1;
							break;
						}
					}
					if(f==0){
						Label l4 = new Label("Incorrect Username or Password");
						l4.setBounds(200,650,200,40);
						add(l4);
					}
					else{
						dispose();
						home_admin h1=new home_admin();
						h1.setSize(1370,730);
						h1.setBackground(Color.LIGHT_GRAY);
						h1.setVisible(true);
					}
				rs.close(); st.close();
			}
		catch(Exception e)
			{
				System.out.println("Exception"+e);
			}
	}
	if(ae.getSource()==b2){
		dispose();
	}
}
}

class login extends Frame implements ActionListener
{ 
	Label l1;Label l2;Label l3;
	TextField t2; TextField t3;
	Button b1; Button b2;
	Image image;
	login()
	{
		setLayout(null);
		Font f1=new Font("Elephant",Font.BOLD,26);
		Font f2=new Font("Elephant",Font.PLAIN,20);
		l1=new Label("Manager Login");
		l1.setBounds(560,50,300,90);
		l1.setFont(f1);
		
		image = Toolkit.getDefaultToolkit().getImage("./clinic_images/manager_login.jpg");
		
		l2=new Label("Username:");
		l2.setBounds(450,470,100,30);
		l2.setFont(f2);
		t2=new TextField();
		t2.setBounds(650,470,200,30);
		t2.setFont(f2);
		
		l3=new Label("Password:");
		l3.setBounds(450,520,100,30);
		l3.setFont(f2);
		t3=new TextField();
		t3.setBounds(650,520,200,30);
		t3.setFont(f2);
		
		b1=new Button("Login");
		b1.setBounds(500,600,100,40);
		b1.setFont(f2);
		b1.addActionListener(this);
		
		b2=new Button("Back");
		b2.setBounds(650,600,100,40);
		b2.setFont(f2);
		b2.addActionListener(this);
		
		add(l1);add(l2);add(l3);
		add(t2);add(t3);
		add(b1); add(b2);
	}
	
	public void paint(Graphics g){
		g.drawImage(image,450,150,430,300,this); // x,y,getWidth(),getHeight()
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent ae)
	{if(ae.getSource()==b1){
		try 
			{
				//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
				Statement st=db.con.createStatement();
				String s1, s2, s3, s4;
				int f=0;
				//st.executeUpdate("delete from teacher where Tech_ID=300");
				
				ResultSet rs=st.executeQuery("select * from manager_login");
				s3=t2.getText();
				s4=t3.getText();
					while(rs.next())
					{
						s1=rs.getString("Username");
						s2=rs.getString("Password");
						if(s1.equals(s3) && s2.equals(s4)){
							f=1;
							break;
						}
					}
					if(f==0){
						Label l4 = new Label("Incorrect Username or Password");
						l4.setBounds(200,650,200,40);
						add(l4);
					}
					else{
						dispose();
						home_manager h1=new home_manager();
						h1.setSize(1370,730);
						h1.setBackground(Color.LIGHT_GRAY);
						h1.setVisible(true);
					}
				rs.close(); st.close();   
			}
		catch(Exception e)
			{
				System.out.println("Exception"+e);
			}
	}
	if(ae.getSource()==b2){
		dispose();
	}
}}

class home_admin extends Frame implements ActionListener
{
	Label l1;
	Button b1; Button b2; Button b3;
	Image image1; Image image2;
	home_admin()
	{
		setLayout(null);
		Font f1=new Font("Elephant",Font.BOLD,26);
		Font f2=new Font("Elephant",Font.PLAIN,16);
		l1=new Label("CLINIC MANAGEMENT SYSTEM");
		l1.setBounds(450,50,500,100);
		l1.setFont(f1);
		add(l1);
		
		image1 = Toolkit.getDefaultToolkit().getImage("./clinic_images/patient.jpg");
		image2 = Toolkit.getDefaultToolkit().getImage("./clinic_images/doctor.jpg");		
		
		b2=new Button("Patient Panel");
		b2.setBounds(400,470,150,100);
		b2.setFont(f2);
		b2.addActionListener(this);
		
		b3=new Button("Doctor Panel");
		b3.setBounds(770,470,150,100);
		b3.setFont(f2);
		b3.addActionListener(this);
		
		b1=new Button("Close");
		b1.setBounds(600,600,100,50);
		b1.setFont(f2);
		b1.addActionListener(this);
		
		add(b1); add(b2); add(b3);
	}
	
	public void paint(Graphics g){
		g.drawImage(image1,330,150,300,300,this); // x,y,getWidth(),getHeight()
		g.drawImage(image2,670,150,300,300,this); // x,y,getWidth(),getHeight()
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			dispose();
		}
		
		if(ae.getSource()==b2){
			manager_patient p1=new manager_patient();
			p1.setSize(1370,730);
			p1.setBackground(Color.LIGHT_GRAY);
			p1.setVisible(true);
		}
		
		if(ae.getSource()==b3){
			admin_doctor d1=new admin_doctor();
			d1.setSize(1370,730);
			d1.setBackground(Color.LIGHT_GRAY);
			d1.setVisible(true);
		}
	}
}

class home_manager extends Frame implements ActionListener
{
	Label l1;
	Button b1; Button b2; Button b3;
	Image image1; Image image2;
	home_manager()
	{
		setLayout(null);
		Font f1=new Font("Elephant",Font.BOLD,26);
		Font f2=new Font("Elephant",Font.PLAIN,16);
		l1=new Label("CLINIC MANAGEMENT SYSTEM");
		l1.setBounds(450,50,500,100);
		l1.setFont(f1);
		add(l1);
		
		image1 = Toolkit.getDefaultToolkit().getImage("./clinic_images/patient.jpg");
		image2 = Toolkit.getDefaultToolkit().getImage("./clinic_images/doctor.jpg");		
		
		b2=new Button("Patient Panel");
		b2.setBounds(400,470,150,100);
		b2.setFont(f2);
		b2.addActionListener(this);
		
		b3=new Button("Doctor Panel");
		b3.setBounds(770,470,150,100);
		b3.setFont(f2);
		b3.addActionListener(this);
		
		b1=new Button("Close");
		b1.setBounds(600,600,100,50);
		b1.setFont(f2);
		b1.addActionListener(this);
		
		add(b1); add(b2); add(b3);
	}
	
	public void paint(Graphics g){
		g.drawImage(image1,330,150,300,300,this); // x,y,getWidth(),getHeight()
		g.drawImage(image2,670,150,300,300,this); // x,y,getWidth(),getHeight()
		super.paint(g);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			dispose();
		}
		
		if(ae.getSource()==b2){
			manager_patient p1=new manager_patient();
			p1.setSize(1370,730);
			p1.setBackground(Color.LIGHT_GRAY);
			p1.setVisible(true);
		}
		
		if(ae.getSource()==b3){
			manager_doctor d1=new manager_doctor();
			d1.setSize(1370,730);
			d1.setBackground(Color.LIGHT_GRAY);
			d1.setVisible(true);
		}
	}
}

class manager_patient extends Frame implements ActionListener
{ 
Label l1; Label l2; Label l3; Label l4; Label l5; Label l6; Label l7; Label l8; Label l9; Label l10; Label l11;Label l12; Label l13; Label l14;
TextField t2; TextField t3; TextField t5; TextField t7; TextField t8;TextField t9;TextField t10;TextField t11; TextField t13;
Choice ch1; Choice ch2; Choice ch3;
JTable table;
DefaultTableModel tableModel;
Button b1; Button b2; Button b3; Button b4; Button b5; Button b6; Button b7;
	manager_patient()
	{
		setLayout(null);
		Font f1=new Font("Elephant",Font.BOLD,26);
		Font f2=new Font("Elephant",Font.PLAIN,20);
		Font f3=new Font("Elephant",Font.PLAIN,18);
		Font f4=new Font("Elephant",Font.PLAIN,12);
		Font f5=new Font("Calibri",Font.PLAIN,12);
		l1=new Label("Patient");
		l1.setBounds(630,10,300,90);
		l1.setFont(f1);
		add(l1);
		
		l2=new Label("Patient ID");
		l2.setBounds(20,100,130,30);
		l2.setFont(f2);
		add(l2);
		t2=new TextField();
		t2.setBounds(170,100,200,30);
		t2.setFont(f2);
		add(t2);
		
		l3=new Label("Patient Name");
		l3.setBounds(20,140,130,30);
		l3.setFont(f2);
		add(l3);
		t3=new TextField();
		t3.setBounds(170,140,200,30);
		t3.setFont(f2);
		add(t3);
		
		l4=new Label("Gender");
		l4.setBounds(20,180,130,30);
		l4.setFont(f2);
		add(l4);
		ch3=new Choice();
		ch3.add("Select");
		ch3.add("Male");
		ch3.add("Female");
		ch3.setBounds(170,180,200,30);
		ch3.setFont(f2);
		add(ch3);
		
		l5=new Label("DOB");
		l5.setBounds(20,220,130,30);
		l5.setFont(f2);
		add(l5);
		t5=new TextField();
		t5.setBounds(170,220,200,30);
		t5.setFont(f2);
		add(t5);
		l6=new Label("DD/MM/YYYY");
		l6.setBounds(170,250,200,30);
		l6.setFont(f4);
		add(l6);
		
		l7=new Label("Phone Number");
		l7.setBounds(20,290,130,30);
		l7.setFont(f2);
		add(l7);
		t7=new TextField();
		t7.setBounds(170,290,200,30);
		t7.setFont(f2);
		add(t7);
		
		l8=new Label("Address");
		l8.setBounds(20,330,130,30);
		l8.setFont(f2);
		add(l8);
		t8=new TextField();
		t8.setBounds(170,330,200,30);
		t8.setFont(f2);
		add(t8);
		
		t9=new TextField();
		t9.setBounds(170,370,100,30);
		t9.setFont(f2);
		add(t9);
		Label l9=new Label("City");
		l9.setBounds(170,400,100,30);
		l9.setFont(f4);
		add(l9);
		t10=new TextField();
		t10.setBounds(270,370,100,30);
		t10.setFont(f2);
		add(t10);
		Label l10=new Label("Pin");
		l10.setBounds(270,400,100,30);
		l10.setFont(f4);
		add(l10);
		
		t11=new TextField();
		t11.setBounds(170,440,200,30);
		t11.setFont(f2);
		add(t11);
		Label l11=new Label("State");
		l11.setBounds(170,470,200,30);
		l11.setFont(f4);
		add(l11);
		
		l12=new Label("Disease");
		l12.setBounds(20,510,130,30);
		l12.setFont(f2);
		add(l12);
		ch1=new Choice();
		ch1.add("Select");
		ch1.add("Infections/ Viral");
		ch1.add("Heart Problems");
		ch1.add("Fractures");
		ch1.add("Eye Sight");
		ch1.add("Throat/Nose/Ear Problems");
		ch1.add("Tooth Problems");
		ch1.setBounds(170,510,200,30);
		ch1.setFont(f2);
		add(ch1);
		
		l13=new Label("Emergency Num");
		l13.setBounds(20,550,150,30);
		l13.setFont(f2);
		add(l13);
		t13=new TextField();
		t13.setBounds(170,550,200,30);
		t13.setFont(f2);
		add(t13);
		
		ch2=new Choice();
		ch2.add("Select");
		ch2.add("Son");
		ch2.add("Daughter");
		ch2.add("Husband");
		ch2.add("Wife");
		ch2.add("Sibling");
		ch2.add("Guardian");
		ch2.setBounds(170,590,200,30);
		ch2.setFont(f2);
		add(ch2);
		l14=new Label("Relationship with patient");
		l14.setBounds(170,620,200,30);
		l14.setFont(f4);
		add(l14);
		
		b1=new Button("Load");
		b1.setBounds(240,655,100,30);
		b1.setFont(f2);
		b1.addActionListener(this);
		add(b1);
		
		b2=new Button("Enroll");
		b2.setBounds(360,655,100,30);
		b2.setFont(f2);
		b2.addActionListener(this);
		add(b2);
		
		b3=new Button("View");
		b3.setBounds(480,655,100,30);
		b3.setFont(f2);
		b3.addActionListener(this);
		add(b3);
		
		b4=new Button("Update");
		b4.setBounds(600,655,100,30);
		b4.setFont(f2);
		b4.addActionListener(this);
		add(b4);
		
		b5=new Button("Delete");
		b5.setBounds(720,655,100,30);
		b5.setFont(f2);
		b5.addActionListener(this);
		add(b5);
		
		b6=new Button("Reset");
		b6.setBounds(840,655,100,30);
		b6.setFont(f2);
		b6.addActionListener(this);
		add(b6);
		
		b7=new Button("Close");
		b7.setBounds(960,655,100,30);
		b7.setFont(f2);
		b7.addActionListener(this);
		add(b7);
		
		table=new JTable();
		tableModel=new DefaultTableModel();
		table.setModel(tableModel);
		table.setBounds(400,100,950,500);
		table.setFont(f5);
		tableModel=new DefaultTableModel();
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(400,100,950,500);
		add(jsp);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == b1) 
		{
            try {
                //Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
                Statement statement = db.con.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM patient");

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                DefaultTableModel model = new DefaultTableModel();
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(metaData.getColumnName(i));
                }

                while (resultSet.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(rowData);
                }
                table.setModel(model);
				
				t2.setText("");
				t3.setText("");
				ch3.select(0);
				t5.setText("");
				t7.setText("");
				t8.setText("");
				t9.setText("");
				t10.setText("");
				t11.setText("");
				ch1.select(0);
				t13.setText("");
				ch2.select(0);
				
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
			}
		}
		
		if ( ae.getSource() == b2 ){
		try 
			{
				//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
				String sql = "INSERT INTO patient (patient_ID, patient_name, gender, DOB, phone_num, address, city, pin, state, disease, emergency_num, relation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement p = db.con.prepareStatement(sql);
								
				p.setInt(1, Integer.parseInt(t2.getText()));
				p.setString(2, t3.getText());
				p.setString(3, ch3.getSelectedItem());
				p.setString(4, t5.getText());
				p.setString(5, t7.getText());
				p.setString(6, t8.getText());
				p.setString(7, t9.getText());
				p.setInt(8, Integer.parseInt(t10.getText()));
				p.setString(9, t11.getText());
				p.setString(10, ch1.getSelectedItem());
				p.setString(11, t13.getText());
				p.setString(12, ch2.getSelectedItem());
				
				p.executeUpdate();
				
				JOptionPane.showMessageDialog(this, "Data saved successfully!");
				p.close(); 
			}
		catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to the database");
        }
	}
	
	if( ae.getSource() == b3 )
		{
		    int srow = table.getSelectedRow();
		
		    if (srow != -1) 
		    {
            int cCount = table.getColumnCount();
            String[] rowData = new String[cCount];

            for (int i = 0; i < cCount; i++) 
			{
               Object value = table.getValueAt(srow, i);
               rowData[i] = value != null ? value.toString() : "";
            }

    
            t2.setText(rowData[0]); 
		    t3.setText(rowData[1]);
		    for (int i = 0; i < ch3.getItemCount(); i++) 
		    {
            if (ch3.getItem(i).equals(rowData[2])) 
			{
                ch3.select(i);
                break;
            }
            }
		    t5.setText(rowData[3]);
		    t7.setText(rowData[4]);
			t8.setText(rowData[5]);
			t9.setText(rowData[6]);
			t10.setText(rowData[7]);
			t11.setText(rowData[8]);
		    for (int i = 0; i < ch1.getItemCount(); i++) 
		    {
            if (ch1.getItem(i).equals(rowData[9])) 
			{
                ch1.select(i);
                break;
            }
            }
		    t13.setText(rowData[10]);
			for (int i = 0; i < ch2.getItemCount(); i++) 
		    {
            if (ch2.getItem(i).equals(rowData[11])) 
			{
                ch2.select(i);
                break;
            }
            }
		    }else
		    {
			  JOptionPane.showMessageDialog(this, "No Row is selected", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}
	
	if ( ae.getSource() == b4 ){
		try{
			//Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
			String updateSQL = "UPDATE patient SET patient_name=?, gender=?, DOB=?, phone_num=?, address=?, city=?, pin=?, state=?, disease=?, emergency_num=?, relation=? WHERE patient_ID=?";
			
			PreparedStatement ps = db.con.prepareStatement(updateSQL);
			
			/*ps.setInt(1, Integer.parseInt(t2.getText()));*/
			ps.setString(1, t3.getText());
			ps.setString(2, ch3.getSelectedItem());
			ps.setString(3, t5.getText());
			ps.setString(4, t7.getText());
			ps.setString(5, t8.getText());
			ps.setString(6, t9.getText());
			ps.setInt(7, Integer.parseInt(t10.getText()));
			ps.setString(8, t11.getText());
			ps.setString(9, ch1.getSelectedItem());
			ps.setString(10, t13.getText());
			ps.setString(11, ch2.getSelectedItem());
			ps.setInt(12, Integer.parseInt(t2.getText()));
			
			int rowAffected = ps.executeUpdate();
			
			if(rowAffected > 0){
				JOptionPane.showMessageDialog(this, "Data updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(this, "No rows updated. Data may not exist or no changes were made.", "Information", JOptionPane.ERROR_MESSAGE);
			}
			
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: "+e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	if ( ae.getSource() == b5 ){
		try{
			int srow = table.getSelectedRow();
			//Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
			Statement statement = db.con.createStatement();
			String deleteSQL = "DELETE FROM patient WHERE patient_ID = ?";
			PreparedStatement pStatement = db.con.prepareStatement(deleteSQL);
			
			pStatement.setString(1, t2.getText());
			int rowAffected = pStatement.executeUpdate();
			JOptionPane.showMessageDialog(this, "Row deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			
			statement.close();
		}catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	if (ae.getSource() == b6 ){
		dispose();
		manager_patient p1=new manager_patient();
		p1.setSize(1370,730);
		p1.setBackground(Color.LIGHT_GRAY);
		p1.setVisible(true);
	}
	
	if ( ae.getSource()== b7 ){
		dispose();
	}
	}
}

class admin_doctor extends Frame implements ActionListener
{ 
Label l1; Label l2; Label l3; Label l4; Label l5; Label l6; Label l7; Label l8; Label l9; Label l10; Label l11;Label l12; Label l13; Label l14;
TextField t2; TextField t3; TextField t5; TextField t7; TextField t8;TextField t9;TextField t10;TextField t11; TextField t13;
Choice ch1; Choice ch2; Choice ch3;
JTable table;
DefaultTableModel tableModel;
Button b1; Button b2; Button b3; Button b4; Button b5; Button b6; Button b7;
	admin_doctor()
	{
		setLayout(null);
		Font f1=new Font("Elephant",Font.BOLD,26);
		Font f2=new Font("Elephant",Font.PLAIN,20);
		Font f3=new Font("Elephant",Font.PLAIN,18);
		Font f4=new Font("Elephant",Font.PLAIN,12);
		Font f5=new Font("Calibri",Font.PLAIN,12);
		l1=new Label("Doctor");
		l1.setBounds(630,10,300,90);
		l1.setFont(f1);
		add(l1);
		
		l2=new Label("Doctor ID");
		l2.setBounds(20,100,130,30);
		l2.setFont(f2);
		add(l2);
		t2=new TextField();
		t2.setBounds(170,100,200,30);
		t2.setFont(f2);
		add(t2);
		
		l3=new Label("Doctor Name");
		l3.setBounds(20,140,130,30);
		l3.setFont(f2);
		add(l3);
		t3=new TextField();
		t3.setBounds(170,140,200,30);
		t3.setFont(f2);
		add(t3);
		
		l4=new Label("Gender");
		l4.setBounds(20,180,130,30);
		l4.setFont(f2);
		add(l4);
		ch3=new Choice();
		ch3.add("Select");
		ch3.add("Male");
		ch3.add("Female");
		ch3.setBounds(170,180,200,30);
		ch3.setFont(f2);
		add(ch3);
		
		l5=new Label("DOB");
		l5.setBounds(20,220,130,30);
		l5.setFont(f2);
		add(l5);
		t5=new TextField();
		t5.setBounds(170,220,200,30);
		t5.setFont(f2);
		add(t5);
		l6=new Label("DD/MM/YYYY");
		l6.setBounds(170,250,200,30);
		l6.setFont(f4);
		add(l6);
		
		l7=new Label("Phone Number");
		l7.setBounds(20,290,130,30);
		l7.setFont(f2);
		add(l7);
		t7=new TextField();
		t7.setBounds(170,290,200,30);
		t7.setFont(f2);
		add(t7);
		
		l8=new Label("Address");
		l8.setBounds(20,330,130,30);
		l8.setFont(f2);
		add(l8);
		t8=new TextField();
		t8.setBounds(170,330,200,30);
		t8.setFont(f2);
		add(t8);
		
		t9=new TextField();
		t9.setBounds(170,370,100,30);
		t9.setFont(f2);
		add(t9);
		Label l9=new Label("City");
		l9.setBounds(170,400,100,30);
		l9.setFont(f4);
		add(l9);
		t10=new TextField();
		t10.setBounds(270,370,100,30);
		t10.setFont(f2);
		add(t10);
		Label l10=new Label("Pin");
		l10.setBounds(270,400,100,30);
		l10.setFont(f4);
		add(l10);
		
		t11=new TextField();
		t11.setBounds(170,440,200,30);
		t11.setFont(f2);
		add(t11);
		Label l11=new Label("State");
		l11.setBounds(170,470,200,30);
		l11.setFont(f4);
		add(l11);
		
		l12=new Label("Civil Status");
		l12.setBounds(20,510,130,30);
		l12.setFont(f2);
		add(l12);
		ch1=new Choice();
		ch1.add("Select");
		ch1.add("Single");
		ch1.add("Married");
		ch1.add("Divorced");
		ch1.add("Widowed");
		ch1.setBounds(170,510,200,30);
		ch1.setFont(f2);
		add(ch1);
		
		l13=new Label("Experience");
		l13.setBounds(20,550,150,30);
		l13.setFont(f2);
		add(l13);
		t13=new TextField();
		t13.setBounds(170,550,200,30);
		t13.setFont(f2);
		add(t13);
		
		ch2=new Choice();
		ch2.add("Select");
		ch2.add("Medicine");
		ch2.add("Cardiologist");
		ch2.add("Orthopaedic");
		ch2.add("Opthalmologist");
		ch2.add("ENT");
		ch2.add("Dentist");
		ch2.setBounds(170,590,200,30);
		ch2.setFont(f2);
		add(ch2);
		l14=new Label("Field of Specialization");
		l14.setBounds(170,620,200,30);
		l14.setFont(f4);
		add(l14);
		
		b1=new Button("Load");
		b1.setBounds(240,655,100,30);
		b1.setFont(f2);
		b1.addActionListener(this);
		add(b1);
		
		b2=new Button("Enroll");
		b2.setBounds(360,655,100,30);
		b2.setFont(f2);
		b2.addActionListener(this);
		add(b2);
		
		b3=new Button("View");
		b3.setBounds(480,655,100,30);
		b3.setFont(f2);
		b3.addActionListener(this);
		add(b3);
		
		b4=new Button("Update");
		b4.setBounds(600,655,100,30);
		b4.setFont(f2);
		b4.addActionListener(this);
		add(b4);
		
		b5=new Button("Delete");
		b5.setBounds(720,655,100,30);
		b5.setFont(f2);
		b5.addActionListener(this);
		add(b5);
		
		b6=new Button("Reset");
		b6.setBounds(840,655,100,30);
		b6.setFont(f2);
		b6.addActionListener(this);
		add(b6);
		
		b7=new Button("Close");
		b7.setBounds(960,655,100,30);
		b7.setFont(f2);
		b7.addActionListener(this);
		add(b7);
		
		table=new JTable();
		tableModel=new DefaultTableModel();
		table.setModel(tableModel);
		table.setBounds(400,100,950,500);
		table.setFont(f5);
		tableModel=new DefaultTableModel();
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(400,100,950,500);
		add(jsp);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == b1) 
		{
            try {
                //Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
                Statement statement = db.con.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM doctor");

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                DefaultTableModel model = new DefaultTableModel();
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(metaData.getColumnName(i));
                }

                while (resultSet.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(rowData);
                }
                table.setModel(model);
				
				t2.setText("");
				t3.setText("");
				ch3.select(0);
				t5.setText("");
				t7.setText("");
				t8.setText("");
				t9.setText("");
				t10.setText("");
				t11.setText("");
				ch1.select(0);
				t13.setText("");
				ch2.select(0);
				
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
			}
		}
		
		if ( ae.getSource() == b2 ){
		try 
			{
				//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
				String sql = "INSERT INTO doctor (doctor_ID, doctor_name, gender, DOB, phone_num, address, city, pin, state, civil_status, experience, specialization) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement p = db.con.prepareStatement(sql);
								
				p.setInt(1, Integer.parseInt(t2.getText()));
				p.setString(2, t3.getText());
				p.setString(3, ch3.getSelectedItem());
				p.setString(4, t5.getText());
				p.setString(5, t7.getText());
				p.setString(6, t8.getText());
				p.setString(7, t9.getText());
				p.setInt(8, Integer.parseInt(t10.getText()));
				p.setString(9, t11.getText());
				p.setString(10, ch1.getSelectedItem());
				p.setString(11, t13.getText());
				p.setString(12, ch2.getSelectedItem());
				
				p.executeUpdate();
				
				JOptionPane.showMessageDialog(this, "Data saved successfully!");
				p.close(); 
			}
		catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to the database");
        }
	}
	
	if( ae.getSource() == b3 )
		{
		    int srow = table.getSelectedRow();
		
		    if (srow != -1) 
		    {
            int cCount = table.getColumnCount();
            String[] rowData = new String[cCount];

            for (int i = 0; i < cCount; i++) 
			{
               Object value = table.getValueAt(srow, i);
               rowData[i] = value != null ? value.toString() : "";
            }

    
            t2.setText(rowData[0]); 
		    t3.setText(rowData[1]);
		    for (int i = 0; i < ch3.getItemCount(); i++) 
		    {
            if (ch3.getItem(i).equals(rowData[2])) 
			{
                ch3.select(i);
                break;
            }
            }
		    t5.setText(rowData[3]);
		    t7.setText(rowData[4]);
			t8.setText(rowData[5]);
			t9.setText(rowData[6]);
			t10.setText(rowData[7]);
			t11.setText(rowData[8]);
		    for (int i = 0; i < ch1.getItemCount(); i++) 
		    {
            if (ch1.getItem(i).equals(rowData[9])) 
			{
                ch1.select(i);
                break;
            }
            }
		    t13.setText(rowData[10]);
			for (int i = 0; i < ch2.getItemCount(); i++) 
		    {
            if (ch2.getItem(i).equals(rowData[11])) 
			{
                ch2.select(i);
                break;
            }
            }
		    }else
		    {
			  JOptionPane.showMessageDialog(this, "No Row is selected", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}
	
	if ( ae.getSource() == b4 ){
		try{
			//Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
			String updateSQL = "UPDATE doctor SET doctor_name=?, gender=?, DOB=?, phone_num=?, address=?, city=?, pin=?, state=?, civil_status=?, experience=?, specialization=? WHERE doctor_ID=?";
			
			PreparedStatement ps = db.con.prepareStatement(updateSQL);
			
			/*ps.setInt(1, Integer.parseInt(t2.getText()));*/
			ps.setString(1, t3.getText());
			ps.setString(2, ch3.getSelectedItem());
			ps.setString(3, t5.getText());
			ps.setString(4, t7.getText());
			ps.setString(5, t8.getText());
			ps.setString(6, t9.getText());
			ps.setInt(7, Integer.parseInt(t10.getText()));
			ps.setString(8, t11.getText());
			ps.setString(9, ch1.getSelectedItem());
			ps.setString(10, t13.getText());
			ps.setString(11, ch2.getSelectedItem());
			ps.setInt(12, Integer.parseInt(t2.getText()));
			
			int rowAffected = ps.executeUpdate();
			
			if(rowAffected > 0){
				JOptionPane.showMessageDialog(this, "Data updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(this, "No rows updated. Data may not exist or no changes were made.", "Information", JOptionPane.ERROR_MESSAGE);
			}
			
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: "+e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	if ( ae.getSource() == b5 ){
		try{
			int srow = table.getSelectedRow();
			//Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
			Statement statement = db.con.createStatement();
			String deleteSQL = "DELETE FROM doctor WHERE doctor_ID = ?";
			PreparedStatement pStatement = db.con.prepareStatement(deleteSQL);
			
			pStatement.setString(1, t2.getText());
			int rowAffected = pStatement.executeUpdate();
			JOptionPane.showMessageDialog(this, "Row deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			
			statement.close();
		}catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	if (ae.getSource() == b6 ){
		dispose();
		admin_doctor d1=new admin_doctor();
		d1.setSize(1370,730);
		d1.setBackground(Color.LIGHT_GRAY);
		d1.setVisible(true);
	}
	
	if ( ae.getSource()== b7 ){
		dispose();
	}
	}
}

class manager_doctor extends Frame implements ActionListener
{ 
Label l1; Label l2; Label l3; Label l4; Label l5; Label l6; Label l7; Label l8; Label l9; Label l10; Label l11;Label l12; Label l13; Label l14;
TextField t2; TextField t3; TextField t5; TextField t7; TextField t8;TextField t9;TextField t10;TextField t11; TextField t13;
Choice ch1; Choice ch2; Choice ch3;
JTable table;
DefaultTableModel tableModel;
Button b1; Button b2; Button b3; Button b4; Button b6; Button b7;
	manager_doctor()
	{
		setLayout(null);
		Font f1=new Font("Elephant",Font.BOLD,26);
		Font f2=new Font("Elephant",Font.PLAIN,20);
		Font f3=new Font("Elephant",Font.PLAIN,18);
		Font f4=new Font("Elephant",Font.PLAIN,12);
		Font f5=new Font("Calibri",Font.PLAIN,12);
		l1=new Label("Doctor");
		l1.setBounds(630,10,300,90);
		l1.setFont(f1);
		add(l1);
		
		l2=new Label("Doctor ID");
		l2.setBounds(20,100,130,30);
		l2.setFont(f2);
		add(l2);
		t2=new TextField();
		t2.setBounds(170,100,200,30);
		t2.setFont(f2);
		add(t2);
		
		l3=new Label("Doctor Name");
		l3.setBounds(20,140,130,30);
		l3.setFont(f2);
		add(l3);
		t3=new TextField();
		t3.setBounds(170,140,200,30);
		t3.setFont(f2);
		add(t3);
		
		l4=new Label("Gender");
		l4.setBounds(20,180,130,30);
		l4.setFont(f2);
		add(l4);
		ch3=new Choice();
		ch3.add("Select");
		ch3.add("Male");
		ch3.add("Female");
		ch3.setBounds(170,180,200,30);
		ch3.setFont(f2);
		add(ch3);
		
		l5=new Label("DOB");
		l5.setBounds(20,220,130,30);
		l5.setFont(f2);
		add(l5);
		t5=new TextField();
		t5.setBounds(170,220,200,30);
		t5.setFont(f2);
		add(t5);
		l6=new Label("DD/MM/YYYY");
		l6.setBounds(170,250,200,30);
		l6.setFont(f4);
		add(l6);
		
		l7=new Label("Phone Number");
		l7.setBounds(20,290,130,30);
		l7.setFont(f2);
		add(l7);
		t7=new TextField();
		t7.setBounds(170,290,200,30);
		t7.setFont(f2);
		add(t7);
		
		l8=new Label("Address");
		l8.setBounds(20,330,130,30);
		l8.setFont(f2);
		add(l8);
		t8=new TextField();
		t8.setBounds(170,330,200,30);
		t8.setFont(f2);
		add(t8);
		
		t9=new TextField();
		t9.setBounds(170,370,100,30);
		t9.setFont(f2);
		add(t9);
		Label l9=new Label("City");
		l9.setBounds(170,400,100,30);
		l9.setFont(f4);
		add(l9);
		t10=new TextField();
		t10.setBounds(270,370,100,30);
		t10.setFont(f2);
		add(t10);
		Label l10=new Label("Pin");
		l10.setBounds(270,400,100,30);
		l10.setFont(f4);
		add(l10);
		
		t11=new TextField();
		t11.setBounds(170,440,200,30);
		t11.setFont(f2);
		add(t11);
		Label l11=new Label("State");
		l11.setBounds(170,470,200,30);
		l11.setFont(f4);
		add(l11);
		
		l12=new Label("Civil Status");
		l12.setBounds(20,510,130,30);
		l12.setFont(f2);
		add(l12);
		ch1=new Choice();
		ch1.add("Select");
		ch1.add("Single");
		ch1.add("Married");
		ch1.add("Divorced");
		ch1.add("Widowed");
		ch1.setBounds(170,510,200,30);
		ch1.setFont(f2);
		add(ch1);
		
		l13=new Label("Experience");
		l13.setBounds(20,550,150,30);
		l13.setFont(f2);
		add(l13);
		t13=new TextField();
		t13.setBounds(170,550,200,30);
		t13.setFont(f2);
		add(t13);
		
		ch2=new Choice();
		ch2.add("Select");
		ch2.add("Medicine");
		ch2.add("Cardiologist");
		ch2.add("Orthopaedic");
		ch2.add("Opthalmologist");
		ch2.add("ENT");
		ch2.add("Dentist");
		ch2.setBounds(170,590,200,30);
		ch2.setFont(f2);
		add(ch2);
		l14=new Label("Field of Specialization");
		l14.setBounds(170,620,200,30);
		l14.setFont(f4);
		add(l14);
		
		b1=new Button("Load");
		b1.setBounds(240,655,100,30);
		b1.setFont(f2);
		b1.addActionListener(this);
		add(b1);
		
		b2=new Button("Enroll");
		b2.setBounds(360,655,100,30);
		b2.setFont(f2);
		b2.addActionListener(this);
		add(b2);
		
		b3=new Button("View");
		b3.setBounds(480,655,100,30);
		b3.setFont(f2);
		b3.addActionListener(this);
		add(b3);
		
		b4=new Button("Update");
		b4.setBounds(600,655,100,30);
		b4.setFont(f2);
		b4.addActionListener(this);
		add(b4);
		
		b6=new Button("Reset");
		b6.setBounds(720,655,100,30);
		b6.setFont(f2);
		b6.addActionListener(this);
		add(b6);
		
		b7=new Button("Close");
		b7.setBounds(840,655,100,30);
		b7.setFont(f2);
		b7.addActionListener(this);
		add(b7);
		
		table=new JTable();
		tableModel=new DefaultTableModel();
		table.setModel(tableModel);
		table.setBounds(400,100,950,500);
		table.setFont(f5);
		tableModel=new DefaultTableModel();
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(400,100,950,500);
		add(jsp);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == b1) 
		{
            try {
                //Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
                Statement statement = db.con.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM doctor");

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                DefaultTableModel model = new DefaultTableModel();
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(metaData.getColumnName(i));
                }

                while (resultSet.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = resultSet.getObject(i);
                    }
                    model.addRow(rowData);
                }
                table.setModel(model);
				
				t2.setText("");
				t3.setText("");
				ch3.select(0);
				t5.setText("");
				t7.setText("");
				t8.setText("");
				t9.setText("");
				t10.setText("");
				t11.setText("");
				ch1.select(0);
				t13.setText("");
				ch2.select(0);
				
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
			}
		}
		
		if ( ae.getSource() == b2 ){
		try 
			{
				//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
				String sql = "INSERT INTO doctor (doctor_ID, doctor_name, gender, DOB, phone_num, address, city, pin, state, civil_status, experience, specialization) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement p = db.con.prepareStatement(sql);
								
				p.setInt(1, Integer.parseInt(t2.getText()));
				p.setString(2, t3.getText());
				p.setString(3, ch3.getSelectedItem());
				p.setString(4, t5.getText());
				p.setString(5, t7.getText());
				p.setString(6, t8.getText());
				p.setString(7, t9.getText());
				p.setInt(8, Integer.parseInt(t10.getText()));
				p.setString(9, t11.getText());
				p.setString(10, ch1.getSelectedItem());
				p.setString(11, t13.getText());
				p.setString(12, ch2.getSelectedItem());
				
				p.executeUpdate();
				
				JOptionPane.showMessageDialog(this, "Data saved successfully!");
				p.close(); 
			}
		catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data to the database");
        }
	}
	
	if( ae.getSource() == b3 )
		{
		    int srow = table.getSelectedRow();
		
		    if (srow != -1) 
		    {
            int cCount = table.getColumnCount();
            String[] rowData = new String[cCount];

            for (int i = 0; i < cCount; i++) 
			{
               Object value = table.getValueAt(srow, i);
               rowData[i] = value != null ? value.toString() : "";
            }

    
            t2.setText(rowData[0]); 
		    t3.setText(rowData[1]);
		    for (int i = 0; i < ch3.getItemCount(); i++) 
		    {
            if (ch3.getItem(i).equals(rowData[2])) 
			{
                ch3.select(i);
                break;
            }
            }
		    t5.setText(rowData[3]);
		    t7.setText(rowData[4]);
			t8.setText(rowData[5]);
			t9.setText(rowData[6]);
			t10.setText(rowData[7]);
			t11.setText(rowData[8]);
		    for (int i = 0; i < ch1.getItemCount(); i++) 
		    {
            if (ch1.getItem(i).equals(rowData[9])) 
			{
                ch1.select(i);
                break;
            }
            }
		    t13.setText(rowData[10]);
			for (int i = 0; i < ch2.getItemCount(); i++) 
		    {
            if (ch2.getItem(i).equals(rowData[11])) 
			{
                ch2.select(i);
                break;
            }
            }
		    }else
		    {
			  JOptionPane.showMessageDialog(this, "No Row is selected", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}
	
	if ( ae.getSource() == b4 ){
		try{
			//Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","2006");
			String updateSQL = "UPDATE doctor SET doctor_name=?, gender=?, DOB=?, phone_num=?, address=?, city=?, pin=?, state=?, civil_status=?, experience=?, specialization=? WHERE doctor_ID=?";
			
			PreparedStatement ps = db.con.prepareStatement(updateSQL);
			
			/*ps.setInt(1, Integer.parseInt(t2.getText()));*/
			ps.setString(1, t3.getText());
			ps.setString(2, ch3.getSelectedItem());
			ps.setString(3, t5.getText());
			ps.setString(4, t7.getText());
			ps.setString(5, t8.getText());
			ps.setString(6, t9.getText());
			ps.setInt(7, Integer.parseInt(t10.getText()));
			ps.setString(8, t11.getText());
			ps.setString(9, ch1.getSelectedItem());
			ps.setString(10, t13.getText());
			ps.setString(11, ch2.getSelectedItem());
			ps.setInt(12, Integer.parseInt(t2.getText()));
			
			int rowAffected = ps.executeUpdate();
			
			if(rowAffected > 0){
				JOptionPane.showMessageDialog(this, "Data updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(this, "No rows updated. Data may not exist or no changes were made.", "Information", JOptionPane.ERROR_MESSAGE);
			}
			
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: "+e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	if (ae.getSource() == b6 ){
		dispose();
		manager_doctor d1=new manager_doctor();
		d1.setSize(1370,730);
		d1.setBackground(Color.LIGHT_GRAY);
		d1.setVisible(true);
	}
	
	if ( ae.getSource()== b7 ){
		dispose();
	}
	}
}

class clinic_login
{
		public static void main(String arg[]){
		mainframe m1=new mainframe();
		m1.setSize(1370,730);
		m1.setBackground(Color.LIGHT_GRAY);
		m1.setVisible(true);
	}
}