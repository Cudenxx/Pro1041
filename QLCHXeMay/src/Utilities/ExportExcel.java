package Utilities;

import Service.CTSanPhamService;
import ServiceImpl.CTSanPhamImpl;
import ViewModel.CTSanPhamHD;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExportExcel {

    private static CTSanPhamService ctSanPhamSevices = new CTSanPhamImpl();

    public void excel(List<CTSanPhamHD> list, String nameSheet) { //tạo và xuất dữ liệu vào một tệp Excel (.xlsx).

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();//tạo một workbook mới, đại diện cho tệp Excel.

            XSSFSheet sheet = workbook.createSheet(nameSheet);// tạo một sheet mới trong workbook
            XSSFRow crow = null;
            Cell cell = null;
            int rownum = 1;
            int count = 0;
            crow = sheet.createRow(0);
            cell = crow.createCell(0, CellType.STRING);//gán các giá trị cho từng ô trong dòng tiêu đề
            cell.setCellValue("STT");
            cell = crow.createCell(1, CellType.STRING);
            cell.setCellValue("Mã Sản Phẩm");
            cell = crow.createCell(2, CellType.STRING);
            cell.setCellValue("Tên Sản Phẩm");
            cell = crow.createCell(3, CellType.STRING);
            cell.setCellValue("Dung Tích Bình Xăng");
            cell = crow.createCell(4, CellType.STRING);
            cell.setCellValue("Dung Tích Xi Lanh");
            cell = crow.createCell(5, CellType.STRING);
            cell.setCellValue("Số Khung");
            cell = crow.createCell(6, CellType.STRING);
            cell.setCellValue("Màu sắc");
            cell = crow.createCell(7, CellType.STRING);
            cell.setCellValue("Xuất Xứ");
            cell = crow.createCell(8, CellType.STRING);
            cell.setCellValue("Loại Xe");
            cell = crow.createCell(9, CellType.STRING);
            cell.setCellValue("Số Lượng");
            for (CTSanPhamHD ctSanPhamHD : list) {//vòng lặp for được sử dụng để duyệt qua danh sách list
                count++;
                Row row = sheet.createRow(rownum++);//tạo một dòng mới trong sheet.
                createList(ctSanPhamHD, row, count);
            }

            FileOutputStream out = new FileOutputStream(new File("ThongKeSanPham.xlsx")); // tạo một đối tượng FileOutputStream để ghi workbook vào tệp Excel
            workbook.write(out);//ghi dữ liệu vào tệp
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createList(CTSanPhamHD ctSanPhamHD, Row row, int count) //tạo và gán giá trị cho các ô trong một dòng trong sheet Excel
    {

        Cell cell = row.createCell(0);// row là dòng trong sheet Excel cần tạo và gán giá trị
        cell.setCellValue(count);// count là số thứ tự của dòng đó trong sheet.

        cell = row.createCell(1);
        cell.setCellValue(ctSanPhamHD.getMaSP());

        cell = row.createCell(2);
        cell.setCellValue(ctSanPhamSevices.getByNameSanPham(ctSanPhamHD.getMaSP()));

        cell = row.createCell(3);
        cell.setCellValue(ctSanPhamSevices.getByNameDTBinhXang(ctSanPhamHD.getMaSP()));

        cell = row.createCell(4);
        cell.setCellValue(ctSanPhamSevices.getByNameDTXiLanh(ctSanPhamHD.getMaSP()));

        cell = row.createCell(5);
        cell.setCellValue(ctSanPhamSevices.getByNameSoKhung(ctSanPhamHD.getMaSP()));

        cell = row.createCell(6);
        cell.setCellValue(ctSanPhamSevices.getByNameMau(ctSanPhamHD.getMaSP()));

        cell = row.createCell(7);
        cell.setCellValue(ctSanPhamSevices.getByNameXuatXu(ctSanPhamHD.getMaSP()));

        cell = row.createCell(8);
        cell.setCellValue(ctSanPhamSevices.getByNameLoaiXe(ctSanPhamHD.getMaSP()));

        cell = row.createCell(9);
        cell.setCellValue(ctSanPhamHD.getSoLuong());
    }
}
