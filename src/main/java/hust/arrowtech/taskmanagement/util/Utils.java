package hust.arrowtech.taskmanagement.util;

import hust.arrowtech.taskmanagement.entity.Project;
import hust.arrowtech.taskmanagement.entity.ProjectSkill;
import hust.arrowtech.taskmanagement.entity.User;
import hust.arrowtech.taskmanagement.entity.UserSkill;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class Utils {
	public static int FILE_EXCEL_HEADER = -1;
	public static int GROUP_HEADER = -2;
	public static int MEMBER = -3;
	

	/**
	 * Display message
	 * 
	 * @param msg
	 */
	public static void addMessage(String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Get point that show convenience of user to project By skill, level, year
	 * 
	 * @param project
	 * @param user
	 * @return
	 */
	public static int getPoint(Project project, User user) {
		List<ProjectSkill> ps = project.getProjectSkills();
		List<UserSkill> us = user.getUserSkills();

		int point = 0;
		int i = 0;
		int j = 0;

		for (i = 0; i < ps.size(); i++)
			for (j = 0; j < us.size(); j++)
				point += calculatePoint(ps.get(i), us.get(j));

		return point;
	}

	/**
	 * Check skill of project skill ps and skill of user skill us by 3d: skill,
	 * level and year
	 * 
	 * @param ps
	 * @param us
	 * @return
	 */
	private static int calculatePoint(ProjectSkill ps, UserSkill us) {
		int point = 0;

		if (ps.getSkill().getId() != us.getSkill().getId())
			return point;

		// Level
		if (ps.getLevel() == us.getLevel())
			point += 2;
		else
			point += (ps.getLevel() > us.getLevel() ? 1 : 3);

		// year of experience
		if (ps.getExperienceYear() == us.getYearOfExperience())
			point += 2;
		else
			point += (ps.getExperienceYear() > us.getYearOfExperience() ? 1 : 3);

		return point;
	}

	/**
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param data
	 */
	public static void writeToExcel(String fileName, String sheetName,
			Map<String, Object[]> data) {
		// create new workbook instance
		HSSFWorkbook workbook = new HSSFWorkbook();
		// create new sheet
		HSSFSheet sheet = workbook.createSheet(sheetName);
		
		sheet.setColumnWidth(2, 8*256);

		// get key set
		Set<String> keySet = data.keySet();
		int rowNumber = 0;

		for (String key : keySet) {
			// create new row
			Row row = sheet.createRow(rowNumber++);
			Object objArray[] = data.get(key);
			int cellNum = 0;
			
			while (cellNum < objArray.length) {
				// create new cell
				Cell cell = row.createCell(cellNum);
				
				if (objArray[cellNum] instanceof Integer) {
					if ((Integer) (objArray[cellNum]) == GROUP_HEADER) {
						// merge 2 begin cells of row
						sheet.addMergedRegion(new CellRangeAddress(rowNumber - 1, rowNumber - 1, cellNum, cellNum + 1));

						cell.setCellValue((String) (objArray[++cellNum]));
					} else if ((Integer)(objArray[cellNum]) != MEMBER){
						cell.setCellValue((Integer)(objArray[cellNum]));
					}
				} else if (objArray[cellNum] instanceof String) {
					cell.setCellValue((String) (objArray[cellNum]));
				} else if (objArray[cellNum] instanceof Boolean) {
					if ((Boolean) (objArray[cellNum]))
						cell.setCellValue("Enable");
					else
						cell.setCellValue("Disable");
				}
				cellNum++;
			}
		}
		
		writeToRespond(workbook, fileName);
	}
	
	
	/**
	 * Write work book to excel file
	 * @param workbook
	 * @param fileName
	 */
	public static void writeToRespond(Workbook workbook, String fileName){
		try {
			// Write the workbook in file system
			final FacesContext fc = FacesContext.getCurrentInstance();
			final ExternalContext ec = fc.getExternalContext();
			ec.setResponseContentType("application/ms-excel; charset=UTF-8");
			ec.setResponseCharacterEncoding("UTF-8");
			ec.setResponseHeader(
					"Content-Disposition",
					"attachment; filename*=UTF-8''"
							+ URLEncoder.encode(fileName, "UTF-8"));
			final OutputStream output = ec.getResponseOutputStream();
			workbook.write(output);
			output.flush();
			output.close();

			fc.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
