package GUI;


import DAO.QuanLyDAO;
import DAO.SinhVienDAO;
import DTO.SinhVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class GiaoDienQuanLy extends JFrame {
    
    private String userID;
    private String password;

    public GiaoDienQuanLy() {
        unitGUI();
    }

    
    public GiaoDienQuanLy(String a, String b) {
        this.userID = a;
        this.password = b;
        unitGUI();
    }

    public void unitGUI() {
        setSize(950, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Ứng dụng quản lí sinh viên");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "title.png").getImage());

        Font font = new Font("Arial", Font.BOLD, 25);
        Font font1 = new Font("Arial", Font.BOLD, 15);

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        JLabel tieuDe = new JLabel("Màn hình Quản Lý");
        tieuDe.setFont(font);

        JLabel xinchao = new JLabel("Xin chào " + "(" + userID + ")");
        JLabel DangXuat = new JLabel("<html><u>Đăng xuất</u></html>");
        DangXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new GiaoDienDNSinhVien();
                dispose();
            }
        });
        DangXuat.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel GioiThieu = new JPanel(new GridLayout(1, 2, 10, 0));
        GioiThieu.add(xinchao);
        GioiThieu.add(DangXuat);
        GioiThieu.setBackground(new Color(213, 232, 212));
        tieuDe.setAlignmentX(Component.CENTER_ALIGNMENT);
        tieuDe.setAlignmentY(Component.CENTER_ALIGNMENT);
        GioiThieu.setAlignmentX(Component.CENTER_ALIGNMENT);
        GioiThieu.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel titlePn = new JPanel();
        titlePn.setLayout(new BoxLayout(titlePn, BoxLayout.Y_AXIS));
        titlePn.add(Box.createVerticalGlue());
        titlePn.add(tieuDe);
        titlePn.add(GioiThieu);
        titlePn.add(Box.createVerticalGlue());
        titlePn.setPreferredSize(new Dimension(0, 60));
        titlePn.setBackground(new Color(213, 232, 212));
        titlePn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //Trang Chủ==========================================================================================
        JPanel trangChu = new JPanel(new BorderLayout());
        JPanel noiDung = new JPanel(new BorderLayout());
        JTextArea NoiDung = new JTextArea();
        NoiDung.setEditable(false);
        try {
            // Mở file và đọc dữ liệu
            FileReader fileReader = new FileReader("./NoiDung.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // Ghi dữ liệu vào JTextArea
                NoiDung.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        noiDung.add(NoiDung, BorderLayout.CENTER);
        JButton thongbao = new JButton("Thêm thông báo!", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "add_1.png"));
        thongbao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame f1 = new JFrame("Thay Đổi Nội Dung");
                f1.setVisible(true);
                f1.setSize(500, 500);
                f1.setLocationRelativeTo(null);
                f1.setLayout(new BorderLayout());
                f1.setIconImage(new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "update_1.png").getImage());

                JTextArea text = new JTextArea();
                text.setText(NoiDung.getText());

                JPanel nut = new JPanel(new GridLayout(1, 2));
                JButton btXacNhan = new JButton("Xác Nhận", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "yes_1.png"));
                btXacNhan.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        NoiDung.setText(text.getText());
                        try {
                            FileWriter writer = new FileWriter("./NoiDung.txt");
                            writer.write(text.getText());
                            writer.close();
                        } catch (IOException evt) {
                            evt.printStackTrace();
                        }
                    }
                });
                JButton btHuy = new JButton("Hủy", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "no_1.png"));
                btHuy.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        f1.dispose();
                    }
                });

                nut.add(btXacNhan);
                nut.add(btHuy);
                f1.add(text, BorderLayout.CENTER);
                f1.add(nut, BorderLayout.SOUTH);

            }
        });

        trangChu.add(noiDung, BorderLayout.CENTER);
        trangChu.add(thongbao, BorderLayout.SOUTH);

        //Danh sách sinh viên==========================================================================================
        JPanel DSSV = new JPanel(new BorderLayout());
        JPanel Loc = new JPanel();
        Loc.setLayout(new BoxLayout(Loc, BoxLayout.X_AXIS));
        
        String columnDSSV[] = {"Mã SV", "Tên Sinh Viên", "Năm sinh", "Giới tính", "Dân tộc", "CCCD/CMND", "Số điện thoại","Mã Khoa","Mã Lớp"};
        DefaultTableModel modelDSSV = new DefaultTableModel(columnDSSV, 0);
        
        ArrayList<SinhVien> svList = QuanLyDAO.selectAll();
        for (SinhVien sv : svList) {
            Object[] duLieu = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(),sv.getDanToc(),sv.getCCCD(),sv.getSoDienThoai(),sv.getMaKhoa(),sv.getMaLop()};
            modelDSSV.addRow(duLieu);
        }
        JTable bangDSSV = new JTable(modelDSSV);
        bangDSSV.getTableHeader().setResizingAllowed(false);
        TableColumnModel columnModel = bangDSSV.getColumnModel();
        
        //Kich Thước Cột
        columnModel.getColumn(0).setPreferredWidth(30); 
        columnModel.getColumn(1).setPreferredWidth(130);
        columnModel.getColumn(3).setPreferredWidth(30);
        columnModel.getColumn(4).setPreferredWidth(30);
        columnModel.getColumn(5).setPreferredWidth(130);
        
        
        bangDSSV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSSV = new JScrollPane(bangDSSV);
        
        
        
        String[] TIMKIEM = {"Tất Cả","Mã Sinh Viên","Tên Sinh Viên", "Năm Sinh","Giới Tính","Dân Tộc","CCCD","Số Điện Thoại","Mã Khoa","Mã Lớp"};
        JComboBox<String> DULIEU = new JComboBox<>(TIMKIEM);
        JTextField jcb1 = new JTextField();
        jcb1.setPreferredSize(new Dimension(40,100));
        JButton bt1 = new JButton("Lọc >>");
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag=false;
                String luaChonTimKiem = (String) DULIEU.getSelectedItem();                     
                String noiDung=new String();
                if(luaChonTimKiem.equalsIgnoreCase("Tất Cả")){
                    ArrayList<SinhVien> svList = QuanLyDAO.selectAll();
                    for (SinhVien sv : svList) {
                        Object[] duLieu = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(),sv.getDanToc(),sv.getCCCD(),sv.getSoDienThoai(),sv.getMaKhoa(),sv.getMaLop()};
                        modelDSSV.addRow(duLieu);
                        flag=true;
                    }
                }
                else if(luaChonTimKiem.equalsIgnoreCase("Tên Sinh Viên"))    noiDung="tenSinhVien";
                else if(luaChonTimKiem.equalsIgnoreCase("Mã Sinh Viên")) noiDung="maSinhvien";
                else if(luaChonTimKiem.equalsIgnoreCase("Dân Tộc")) noiDung="danToc";
                else if(luaChonTimKiem.equalsIgnoreCase("Giới Tính")) noiDung="gioiTinh";
                else if(luaChonTimKiem.equalsIgnoreCase("Năm Sinh")) noiDung="namSinh";
                else if(luaChonTimKiem.equalsIgnoreCase("CCCD")) noiDung="CCCD";
                else if(luaChonTimKiem.equalsIgnoreCase("Số Điện Thoại")) noiDung="soDienThoai";
                else if(luaChonTimKiem.equalsIgnoreCase("Mã Khoa")) noiDung="maKhoa";
                else  noiDung="maLop";
                if(!flag){
                ArrayList<SinhVien> list=QuanLyDAO.selectByCondition(noiDung, jcb1.getText());
                DefaultTableModel model = (DefaultTableModel) bangDSSV.getModel();
                model.setRowCount(0);
                for (SinhVien sv : list) {
                    Object[] duLieuTimKiem = {sv.getMaSinhVien(), sv.getTenSinhVien(), sv.getNamSinh(), sv.getGioiTinh(),sv.getDanToc(),sv.getCCCD(),sv.getSoDienThoai(),sv.getMaKhoa(),sv.getMaLop()};
                    modelDSSV.addRow(duLieuTimKiem);
                    jcb1.setText("");
                    }
                }
            }
        });
        Loc.add(DULIEU);
        Loc.add(jcb1);
        Loc.add(bt1);
        JPanel chonLoc = new JPanel(new GridLayout(1,2));
        chonLoc.add(Loc);
        chonLoc.add(new JPanel());
        
        JPanel pn1 = new JPanel();
        
        
        //Năm Sinh
        UtilDateModel dateModel = new UtilDateModel();
        dateModel.setDate(2000, 0, 1); // Đặt giá trị ban đầu cho model
        dateModel.setSelected(true); // Chọn giá trị ban đầu
        
        // Tạo DatePicker
        JDatePicker namsinh = new JDateComponentFactory().createJDatePicker(dateModel);
        namsinh.setTextEditable(false); // Cho phép nhập ngày tháng năm bằng tay
        namsinh.setShowYearButtons(true); // Hiển thị nút điều khiển chọn năm
        namsinh.setButtonFocusable(true); // Cho phép focus vào nút điều khiển
        JPanel ngaysinh=new JPanel(new BorderLayout());
        ngaysinh.add((Component) namsinh,BorderLayout.CENTER);
        ngaysinh.setPreferredSize(new Dimension(200, 30));

        JTextField msv = new JTextField();
        msv.setPreferredSize(new Dimension(130, 30));
        JLabel lbm = new JLabel("Mã sv: ");
        JTextField tensv = new JTextField();
        tensv.setPreferredSize(new Dimension(200, 30));
        JLabel lbt = new JLabel("Tên: ");
        JTextField lop = new JTextField();
        lop.setPreferredSize(new Dimension(70, 30));
        JLabel lbl = new JLabel("Mã Lớp");
        JLabel lbk = new JLabel("Mã Khoa");
        JTextField khoa = new JTextField();
        khoa.setPreferredSize(new Dimension(70, 30));
        JLabel lbn = new JLabel("Năm sinh");
        String[] genders = {"Nam", "Nữ"};
        JComboBox<String> gioitinh = new JComboBox<>(genders);
        JLabel lbg = new JLabel("Giới tính: ");
        JTextField dantoc = new JTextField();
        dantoc.setPreferredSize(new Dimension(60, 30));
        JLabel lbd = new JLabel("Dân tộc");
        JTextField cccd = new JTextField();
        cccd.setPreferredSize(new Dimension(180, 30));
        JLabel lbc = new JLabel("CCCĐ/CMND");
        JTextField sdt = new JTextField();
        sdt.setPreferredSize(new Dimension(130, 30));
        JLabel lbs = new JLabel("Số ĐT:");
        pn1.setLayout(new FlowLayout());
        pn1.add(lbm);
        pn1.add(msv);
        pn1.add(lbt);
        pn1.add(tensv);
        pn1.add(lbg);
        pn1.add(gioitinh);
        pn1.add(lbl);
        pn1.add(lop);
        pn1.add(lbk);
        pn1.add(khoa);
        
        

        pn1.setPreferredSize(new Dimension(0, 50));
        JPanel pn2 = new JPanel();
        pn2.setLayout(new FlowLayout());
        
        pn2.add(lbn);
        pn2.add(ngaysinh);
        pn2.add(lbd);
        
        pn2.add(dantoc);
        pn2.add(lbc);
        pn2.add(cccd);
        pn2.add(lbs);
        pn2.add(sdt);
       
        JPanel pn3 = new JPanel();
        
        JButton them = new JButton("Thêm");
        them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String MaSV = msv.getText();
                String TenSV = tensv.getText();
                String Lop = lop.getText();
                
                
                Date dateOfBirth = (Date) namsinh.getModel().getValue();
                // Định dạng lại định dạng ngày tháng năm
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = dateFormat.format(dateOfBirth);
                
                String GioiTinh = (String) gioitinh.getSelectedItem();
                String CCCD = cccd.getText();
                String Sdt = sdt.getText();
                String DanToc = dantoc.getText();
                String maKhoa=khoa.getText();
                SinhVien sv=new SinhVien(MaSV,TenSV,dateString,GioiTinh,DanToc,CCCD,Sdt,maKhoa,Lop);
                
                if(QuanLyDAO.insert(sv)){
                    JOptionPane.showMessageDialog(new JFrame(), "Đã Thêm Thành Công");
                    Object[] rowData = {MaSV, TenSV,dateString, GioiTinh, DanToc, CCCD, Sdt,maKhoa,Lop};
                    modelDSSV.addRow(rowData);
                    bangDSSV.setModel(modelDSSV);
                    
                    msv.setText("");
                    tensv.setText("");
                    dantoc.setText("");
                    lop.setText("");
                    gioitinh.setSelectedIndex(0);
                    cccd.setText("");
                    sdt.setText("");
                    dantoc.setText("");
                    khoa.setText("");
                }
                else JOptionPane.showMessageDialog(new JFrame(), "Thêm Không Thành Công");
                
            }
        });
        
        
        JButton sua = new JButton("Sửa");
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bangDSSV.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Hãy chọn một dòng để thực hiện tác vụ");
                    return;
                }
                else{
                JFrame btSua=new JFrame ("Sửa Đổi Thông Tin");
                btSua.setSize(500,500);
                btSua.setVisible(true);
                btSua.setLocationRelativeTo(null);
                btSua.setLayout(new BorderLayout());
                
                TableModel model = bangDSSV.getModel();
                int columnCount = bangDSSV.getColumnCount();
                Object value[]= new Object[columnCount];
                String SVSua[]=new String[columnCount];
                for(int i=0;i<columnCount;i++){
                    value[i] = model.getValueAt(selectedRow, i);
                    SVSua[i] = value[i].toString();
                }
                
                SinhVien sv= new SinhVien();
                sv=SinhVienDAO.selectById(SVSua[0]);
                JPanel pnTTCN= new JPanel();
                pnTTCN.setLayout(new BoxLayout(pnTTCN, BoxLayout.Y_AXIS));
                JPanel pnTTCN1 = new JPanel(new GridLayout(9, 1));

                //CENTER-MaSV
                JPanel MaSV = new JPanel(new FlowLayout());
                JLabel maSv = new JLabel("Mã Sinh Viên");
                maSv.setPreferredSize(new Dimension(100, 30));
                JLabel tfTTCN1 = new JLabel();
                tfTTCN1.setPreferredSize(new Dimension(200, 30));
                tfTTCN1.setText(SVSua[0]);
                MaSV.add(maSv);
                MaSV.add(tfTTCN1);
                pnTTCN1.add(MaSV);

                //CENTER-TenSV
                JPanel TenSV = new JPanel(new FlowLayout());
                JLabel TenSv = new JLabel("Họ Và Tên");
                TenSv.setPreferredSize(new Dimension(100, 30));
                TenSv.setSize(100, 100);
                JTextField tfTTCN2 = new JTextField();
                tfTTCN2.setText(SVSua[1]);
                tfTTCN2.setPreferredSize(new Dimension(200, 30));
                TenSV.add(TenSv);
                TenSV.add(tfTTCN2);
                pnTTCN1.add(TenSV);

                //CENTER-Nam Sinh
                JPanel NamSinh = new JPanel(new FlowLayout());
                JLabel namSinh = new JLabel("Năm Sinh");
                namSinh.setPreferredSize(new Dimension(100, 30));
                namSinh.setSize(100, 100);
                JTextField tfTTCN3 = new JTextField();
                tfTTCN3.setText(SVSua[2]);
                tfTTCN3.setPreferredSize(new Dimension(200, 30));
                NamSinh.add(namSinh);
                NamSinh.add(tfTTCN3);
                pnTTCN1.add(NamSinh);

                //CENTER-Giới Tính
                 JPanel GioiTinh = new JPanel(new FlowLayout());
                JLabel gioitinh = new JLabel("Giới Tính");
                String[] luaChonTTCN = {"Nam", "Nữ"};
                JComboBox<String> LuaChonTTCN = new JComboBox<>(luaChonTTCN);
                if(SVSua[3].equalsIgnoreCase("Nam")){
                    LuaChonTTCN.setSelectedIndex(0);
                }
                else LuaChonTTCN.setSelectedIndex(1);
                gioitinh.setPreferredSize(new Dimension(100, 30));
                LuaChonTTCN.setPreferredSize(new Dimension(200, 30));
                GioiTinh.add(gioitinh);
                GioiTinh.add(LuaChonTTCN);
                pnTTCN1.add(GioiTinh);

                //CENTER-Dân Tộc
                JPanel DanToc = new JPanel(new FlowLayout());
                JLabel dantoc = new JLabel("Dân Tộc");
                dantoc.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN5 = new JTextField();
                tfTTCN5.setText(SVSua[4]);
                tfTTCN5.setPreferredSize(new Dimension(200, 30));
                DanToc.add(dantoc);
                DanToc.add(tfTTCN5);
                pnTTCN1.add(DanToc);

                //CENTER-CCCD
                JPanel CCCD = new JPanel(new FlowLayout());
                JLabel cccd = new JLabel("CCCD/CMND");
                cccd.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN6 = new JTextField();
                tfTTCN6.setText(SVSua[5]);
                tfTTCN6.setPreferredSize(new Dimension(200, 30));
                CCCD.add(cccd);
                CCCD.add(tfTTCN6);
                pnTTCN1.add(CCCD);

                //CENTER-Sđt
                JPanel SDT = new JPanel(new FlowLayout());
                JLabel sdt = new JLabel("Số Điện Thoại");
                sdt.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN7 = new JTextField();
                tfTTCN7.setText(SVSua[6]);
                tfTTCN7.setPreferredSize(new Dimension(200, 30));
                SDT.add(sdt);
                SDT.add(tfTTCN7);
                pnTTCN1.add(SDT);

                //Mã Khoa
                JPanel KHOA = new JPanel(new FlowLayout());
                JLabel khoa = new JLabel("Mã Khoa");
                khoa.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN8 = new JTextField();
                tfTTCN8.setText(SVSua[7]);
                tfTTCN8.setPreferredSize(new Dimension(200, 30));
                KHOA.add(khoa);
                KHOA.add(tfTTCN8);
                pnTTCN1.add(KHOA);

                //Mã Lớp
                JPanel LOP = new JPanel(new FlowLayout());
                JLabel lop = new JLabel("Mã Lớp");
                lop.setPreferredSize(new Dimension(100, 30));
                JTextField tfTTCN9 = new JTextField();
                tfTTCN9.setText(SVSua[8]);
                tfTTCN9.setPreferredSize(new Dimension(200, 30));
                LOP.add(lop);
                LOP.add(tfTTCN9);
                pnTTCN1.add(LOP);

                pnTTCN.add(pnTTCN1);
                btSua.add(pnTTCN,BorderLayout.CENTER);
                
                JPanel btXacNhan=new JPanel(new FlowLayout());
                JButton XacNhan=new JButton("Xác Nhận");
                XacNhan.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String gioiTinh = (String) LuaChonTTCN.getSelectedItem();
                        String TenSV=tfTTCN2.getText();
                        String namSinh=tfTTCN3.getText();             
                        String danToc=tfTTCN5.getText();
                        String CCCD=tfTTCN6.getText();
                        String sdt=tfTTCN7.getText();
                        String maKhoa=tfTTCN8.getText();
                        String maLop=tfTTCN9.getText();
                        SinhVien sv=new SinhVien(SVSua[0],TenSV,namSinh,gioiTinh,danToc,CCCD,sdt,maKhoa,maLop);
                        if(QuanLyDAO.update(sv)&&TenSV!=null&&namSinh!=null&&danToc!=null&&CCCD!=null&&sdt!=null&&maKhoa!=null&&maLop!=null){
                            JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Thành Công");
                            bangDSSV.setValueAt(TenSV,selectedRow, 1);
                            bangDSSV.setValueAt(namSinh,selectedRow, 2);
                            bangDSSV.setValueAt(gioiTinh,selectedRow, 3);
                            bangDSSV.setValueAt(danToc,selectedRow, 4);
                            bangDSSV.setValueAt(CCCD,selectedRow, 5);
                            bangDSSV.setValueAt(sdt,selectedRow, 6);
                            bangDSSV.setValueAt(maKhoa,selectedRow, 7);
                            bangDSSV.setValueAt(maLop,selectedRow, 8);
                            btSua.dispose();
                        }
                        else JOptionPane.showMessageDialog(new JFrame(), "Sửa Thông Tin Không Thành Công");
                    } 
            });
                JButton Huy=new JButton("Hủy");
                Huy.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            btSua.dispose();
                        } 
            });
                btXacNhan.add(XacNhan);
                btXacNhan.add(Huy);
                btSua.add(btXacNhan,BorderLayout.SOUTH);
            
            }
            }
        });

        JButton xoa = new JButton("Xóa");
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel model = bangDSSV.getModel();
                int selectedRow = bangDSSV.getSelectedRow();
                Object value = model.getValueAt(selectedRow, 0); // Lấy giá trị của ô đầu tiên trong dòng đã chọn
                // Lưu giá trị vào biến
                String MaSVXoa = value.toString();
                
                if (QuanLyDAO.delete(MaSVXoa)){
                    JOptionPane.showMessageDialog(new JFrame(), "Đã Xóa Thành Công");
                    if (selectedRow != -1) {
                    modelDSSV.removeRow(selectedRow);
                }
                }
                else JOptionPane.showMessageDialog(new JFrame(), "Không Tìm Thấy Sinh Vien Cần Xóa");
            }
        });
        JButton timKiem = new JButton("Tìm Kiếm");
        
        JButton xemdiem = new JButton("Xem điểm");
        JButton xemtkb = new JButton("Xem TKB");
        pn3.setLayout(new GridLayout(1, 6, 75, 50));
        pn3.add(them);
        pn3.add(sua);
        pn3.add(xoa);
        pn3.add(timKiem);
        pn3.add(xemdiem);
        pn3.add(xemtkb);
        pn3.setPreferredSize(new Dimension(0, 55));
        JPanel pnp = new JPanel();
        pnp.setPreferredSize(new Dimension(0, 130));
//        pnp.setLayout(new GridLayout(4, 1, 0, 10));
        pnp.setLayout(new BoxLayout(pnp, BoxLayout.Y_AXIS));
        pnp.add(pn1);
        pnp.add(pn2);
        pnp.add(pn3);
        pnp.add(chonLoc);

        pnp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSSV.add(scrollPaneDSSV, BorderLayout.CENTER);
        DSSV.add(pnp, BorderLayout.NORTH);

        //Danh sách giảng viên
        JPanel DSGV = new JPanel(new BorderLayout());
        JPanel locgv = new JPanel();
        locgv.setLayout(new BoxLayout(locgv, BoxLayout.X_AXIS));
        JLabel lbgv = new JLabel("Nhập mã giảng viên: ");
        JTextField jcbgv = new JTextField();
        JButton btlocgv = new JButton("Tìm kiếm");
        locgv.add(lbgv);
        locgv.add(jcbgv);
        locgv.add(btlocgv);
        JPanel chonlocgv = new JPanel(new GridLayout(1, 2));
        chonlocgv.add(locgv);
        chonlocgv.setPreferredSize(new Dimension(0, 40));
        String columnDSGV[] = {"Mã GV", "Tên giảng viên", "Trình độ", "Số điện thoại", "Email"};
        DefaultTableModel modelDSGV = new DefaultTableModel(columnDSGV, 0);
        JTable bangDSGV = new JTable(modelDSGV);
        bangDSGV.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSGV = new JScrollPane(bangDSGV);
        JPanel pn4 = new JPanel();
        JTextField mGV = new JTextField();
        JLabel lbmg = new JLabel("Mã GV: ");
        JTextField tenGV = new JTextField();
        JLabel lbtg = new JLabel("Tên giảng viên: ");
        JTextField gioitinhgv = new JTextField();
        JLabel lbgg = new JLabel("Giới tính: ");
        JTextField trinhdo = new JTextField();
        JLabel lbtd = new JLabel("Trình độ ");
        JTextField sdtgv = new JTextField();
        JLabel lbsg = new JLabel("Số ĐT:");
        JTextField email = new JTextField();
        JLabel lbe = new JLabel("Email:");
        pn4.setLayout(new GridLayout(1, 4, 10, 10));
        pn4.add(lbmg);
        pn4.add(mGV);
        pn4.add(lbtg);
        pn4.add(tenGV);
        pn4.add(lbgg);
        pn4.add(gioitinhgv);
        pn4.setPreferredSize(new Dimension(0, 50));
        JPanel pn5 = new JPanel();
        pn5.setLayout(new GridLayout(1, 4, 10, 10));
        pn5.add(lbtd);
        pn5.add(trinhdo);
        pn5.add(lbsg);
        pn5.add(sdtgv);
        pn5.add(lbe);
        pn5.add(email);
        pn5.setPreferredSize(new Dimension(0, 50));
        JPanel pn6 = new JPanel();
        JButton themg = new JButton("Thêm");
        JButton suag = new JButton("Sửa");
        JButton xoag = new JButton("Xóa");
        JButton xemlichgiang = new JButton("Xem lịch giảng");
        pn6.setLayout(new GridLayout(1, 4, 75, 50));
        pn6.add(themg);
        pn6.add(suag);
        pn6.add(xoag);
        pn6.add(xemlichgiang);
        pn6.setPreferredSize(new Dimension(0, 55));
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(0, 130));
        pn.setLayout(new GridLayout(4, 1, 0, 10));
        pn.add(pn4);
        pn.add(pn5);
        pn.add(pn6);
        pn.add(chonlocgv);
        pn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSGV.add(scrollPaneDSGV, BorderLayout.CENTER);
        DSGV.add(pn, BorderLayout.NORTH);

        //DSMH
        JPanel DSMH = new JPanel(new BorderLayout());
        String columnDSMH[] = {"Mã MH", "Tên môn học", "Tiết bắt đầu", "Giảng Viên", "Ngày bắt đầu"};
        DefaultTableModel modelDSMH = new DefaultTableModel(columnDSMH, 0);
        JTable bangDSMH = new JTable(modelDSMH);
        bangDSMH.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPaneDSMH = new JScrollPane(bangDSMH);
        JPanel pn7 = new JPanel();
        JTextField mMH = new JTextField();
        JLabel lbmmh = new JLabel("Mã MH: ");
        JTextField tenMH = new JTextField();
        JLabel lbtm = new JLabel("Tên môn học: ");
        String[] tbd = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox<String> tietbatdau = new JComboBox<>(tbd);
        JLabel lbtbd = new JLabel("Tiết bắt đầu: ");
        JComboBox<String> sotiet = new JComboBox<>(tbd);
        JLabel lbst = new JLabel("Số tiết: ");
        JTextField giangvien  = new JTextField();
        JLabel lbtgv = new JLabel("Giảng viên ");
        JTextField ngaybd = new JTextField();
        JLabel lbnbd = new JLabel("Ngày bắt đầu: ");
        pn7.setLayout(new GridLayout(1, 4, 10, 10));
        pn7.add(lbmmh);
        pn7.add(mMH);
        pn7.add(lbtm);
        pn7.add(tenMH);
        pn7.add(lbtbd);
        pn7.add(tietbatdau);
        pn7.setPreferredSize(new Dimension(0, 50));
        JPanel pn8 = new JPanel();
        pn8.setLayout(new GridLayout(1, 4, 10, 10));
        pn8.add(lbst);
        pn8.add(sotiet);
        pn8.add(lbtgv);
        pn8.add(giangvien);
        pn8.add(lbnbd);
        pn8.add(ngaybd);
        pn8.setPreferredSize(new Dimension(0, 80));
        JPanel pn9 = new JPanel();
        JButton t = new JButton("Thêm");
        JButton s = new JButton("Sửa");
        JButton x = new JButton("Xóa");
        pn9.setLayout(new GridLayout(1, 3, 75, 50));
        pn9.add(t);
        pn9.add(s);
        pn9.add(x);
        pn9.setPreferredSize(new Dimension(0, 55));
        JPanel pnmh = new JPanel();
        pnmh.setPreferredSize(new Dimension(0, 85));
        pnmh.setLayout(new GridLayout(3, 1, 0, 10));
        pnmh.add(pn7);
        pnmh.add(pn8);
        pnmh.add(pn9);
        pnmh.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        DSMH.add(scrollPaneDSMH, BorderLayout.CENTER);
        DSMH.add(pnmh, BorderLayout.NORTH);

        //Tình trạng học phí
        JPanel TTHP = new JPanel(new BorderLayout());
        JLabel HP = new JLabel("Tình trạng Học phí");
        HP.setAlignmentX(Component.CENTER_ALIGNMENT);
        HP.setAlignmentY(Component.CENTER_ALIGNMENT);
        HP.setFont(font1);
        JPanel titlePnTTHP = new JPanel();
        titlePnTTHP.setLayout(new BoxLayout(titlePnTTHP, BoxLayout.Y_AXIS));
        titlePnTTHP.add(Box.createVerticalGlue());
        titlePnTTHP.add(HP);
        titlePnTTHP.add(Box.createVerticalGlue());
        titlePnTTHP.setPreferredSize(new Dimension(0, 40));
        String columnTTHP[] = {"Mã sinh viên", "Tên sinh viên", "Lớp", "Học kỳ", "Năm học", "Học phí", "Trường hợp miễn giảm", "Tổng", "Tình trạng"};
        DefaultTableModel modelTTHP = new DefaultTableModel(columnTTHP, 0);
        JTable tableTTHP = new JTable(modelTTHP);
        tableTTHP.getTableHeader().setReorderingAllowed(false);

        tableTTHP.setBorder(border);
        JPanel pnTTHP = new JPanel();
        pnTTHP.setPreferredSize(new Dimension(0, 25));

        TTHP.add(new JScrollPane(tableTTHP), BorderLayout.CENTER);
        TTHP.add(titlePnTTHP, BorderLayout.NORTH);
        TTHP.add(pnTTHP, BorderLayout.SOUTH);

        // Thêm Vô tabbedPane
        JTabbedPane thanhChon = new JTabbedPane();
        thanhChon.addTab("Trang Chủ", new ImageIcon(GiaoDienDNSinhVien.getPathIcon() + "home_1.png"), trangChu);
        thanhChon.addTab("Danh sách SV", DSSV);
        thanhChon.addTab("Danh sách GV", DSGV);
        thanhChon.addTab("Danh sách MH", DSMH);
        thanhChon.addTab("Học phí", TTHP);

        //Thêm Vào Jframe
        add(titlePn, BorderLayout.NORTH);
        add(thanhChon, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.SOUTH);

    }
    public static void main(String[] args){
        new GiaoDienQuanLy();
    }
}