package hust.arrowtech.taskmanagement.Bean;

import hust.arrowtech.taskmanagement.entity.Skill;
import hust.arrowtech.taskmanagement.entity.SkillCategory;
import hust.arrowtech.taskmanagement.service.CategoryController;
import hust.arrowtech.taskmanagement.service.SkillController;
import hust.arrowtech.taskmanagement.util.Page;
import hust.arrowtech.taskmanagement.util.Topic;
import hust.arrowtech.taskmanagement.util.Utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

@Named
@ViewScoped
public class SkillCategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2406018016768670150L;

	private List<SkillCategory> categories;
	private SkillCategory category;
	private Skill skill;

	private String status;
	private String path;
	private boolean addCategoryDlgShow;
	private boolean addSkillDlgShow;
	private boolean editCategoryDlgShow;

	@Inject
	IndexBean indexBean;
	@Inject
	CategoryController cController;
	@Inject
	SkillController sController;

	public SkillCategoryBean() {
	}

	/**************************************/
	/* START GETTER AND SETTER METHOD */

	public List<SkillCategory> getCategories() {
		if (categories == null) {
			categories = cController.getAllCategories();
		}

		return categories;
	}

	public void setCategories(List<SkillCategory> categories) {
		this.categories = categories;
	}

	public SkillCategory getCategory() {
		return category;
	}

	public void setCategory(SkillCategory category) {
		this.category = category;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isEditCategoryDlgShow() {
		return editCategoryDlgShow;
	}

	public void setEditCategoryDlgShow(boolean editCategoryDlgShow) {
		this.editCategoryDlgShow = editCategoryDlgShow;
	}

	public boolean isAddCategoryDlgShow() {
		return addCategoryDlgShow;
	}

	public void setAddCategoryDlgShow(boolean addCategoryDlgShow) {
		this.addCategoryDlgShow = addCategoryDlgShow;
	}

	public boolean isAddSkillDlgShow() {
		return addSkillDlgShow;
	}

	public void setAddSkillDlgShow(boolean addSkillDlgShow) {
		this.addSkillDlgShow = addSkillDlgShow;
	}

	/* END GETTER AND SETTER METHOD */
	/**************************************/
	/**
	 * Do when constructed
	 */
	@PostConstruct
	public void postConstruct() {
		this.category = new SkillCategory();
		this.skill = new Skill();

		this.path = Page.SKILL_CATEGORY_BASIC_INFO;
		this.status = "Disable";
		this.addCategoryDlgShow = false;
		this.addSkillDlgShow = false;
		this.editCategoryDlgShow = false;
	}

	/**
	 * On status checkbox of add new category dialog click
	 */
	public void onStatusCheckboxClick() {
		if (this.status.equals("Disable")) {
			this.status = "Enable";
		} else {
			this.status = "Disable";
		}
	}

	/**
	 * On add new category button click
	 */
	public void onAddCategoryBtnClick() {
		this.addCategoryDlgShow = true;
	}

	/**
	 * On addCategoryDialog's add button click
	 */
	public void onAddCategoryDlgAddBtnClick() {
		if (checkAddCategoryForm()) {
			category = cController.add(category);
			Utils.addMessage("Added new category \"" + category.getName()
					+ "\"");

			this.addCategoryDlgShow = false;
			reinit();
		}
	}

	/**
	 * On addCategoryDialog's Cancel button click
	 */
	public void onAddCategoryDlgCancelBtnClick() {
		this.addCategoryDlgShow = false;
	}

	/**
	 * on basicInformation link click
	 */
	public void onBasicInformationLinkClick() {
		this.path = Page.SKILL_CATEGORY_BASIC_INFO;
	}

	/**
	 * on skills set link click
	 */
	public void onSkillSetLinkClick() {
		this.path = Page.SKILL_CATEGORY_SKILL_SET;
	}

	/**
	 * on edit link click
	 */
	public void onEditLinkClick() {
		this.editCategoryDlgShow = true;
	}

	/**
	 * 
	 */
	public void onEditCategoryDlgAddBtnClick() {
		if (checkAddCategoryForm()) {
			category = cController.update(category);

			Utils.addMessage("Saved");
			this.editCategoryDlgShow = false;
		}
	}

	/**
	 * 
	 */
	public void onEditCategoryDlgCancelBtnClick() {
		this.editCategoryDlgShow = false;
	}

	/**
	 * on add skill button click
	 */
	public void onAddSkillBtnClick() {
		this.addSkillDlgShow = true;
	}

	/**
	 * on addSkillDialog's add button click
	 */
	public void onAddSkillDlgAddBtnClick() {
		if (checkAddSkillForm()) {
			skill.setSkillCategory(category);

			category = cController.addSkill(category, skill);
			Utils.addMessage("Added skill \"" + skill.getName()
					+ "\" to category \"" + category.getName() + "\"");

			this.addSkillDlgShow = false;
			skill = new Skill();
		}
	}

	/**
	 * on addSkillDialog's cancel button click
	 */
	public void onAddSkillDlgCancelBtnClick() {
		this.addSkillDlgShow = false;
	}

	/**
	 * Check inputed add skill form
	 * 
	 * @return
	 */
	public boolean checkAddSkillForm() {
		// check name
		if (skill.getName().equals("")) {
			Utils.addMessage("Please input name field!");
			return false;
		}

		// Check description
		if (skill.getDescription().equals("")) {
			Utils.addMessage("Please input description field!");
			return false;
		}

		return true;
	}

	/**
	 * Check inputed form
	 * 
	 * @return
	 */
	public boolean checkAddCategoryForm() {
		// Check name
		if (category.getName().equals("")) {
			Utils.addMessage("Please input name field!");
			return false;
		}

		// Check description
		if (category.getDescription().equals("")) {
			Utils.addMessage("Please input description field!");
			return false;
		}

		return true;

	}

	/**
	 * Do when select a row
	 * 
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) {
		category = (SkillCategory) event.getObject();

		indexBean.setPath(Page.SKILL_CATEGORY_VIEW);
		indexBean.setTopic(Topic.SKILL_CATEGORY_INFORMATION);
	}

	/**
	 * Reinit
	 */
	public void reinit() {
		this.categories = null;
		this.category = new SkillCategory();
	}

	/**
	 * Show status is enable or disable
	 * 
	 * @return
	 */
	public String showStatus() {
		if (category == null)
			return "Disable";

		return (category.getStatus()) ? "Enable" : "Disable";
	}

	/**
	 * 
	 * @param event
	 */
	public void onTabChange(TabChangeEvent event) {
		String tabTitle = event.getTab().getTitle();

		if (tabTitle.equals("Basic information")) {
			onBasicInformationLinkClick();
		} else {
			onSkillSetLinkClick();
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void onCellEdit(CellEditEvent event) {
		Skill skill = (Skill) category.getSkills().get(event.getRowIndex());

		skill = sController.update(skill);
		Utils.addMessage("Saved!");
	}

	/**
	 * on export button click
	 */
	public void onExportBtnClick() {
		String fileName = "skill_category.xls";
		String sheetName = "Skill category";

		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		int i = 0;
		
		data.put(i++ + "", new Object[]{Utils.GROUP_HEADER, "Name", "Description", "Status"});

		for (SkillCategory category : categories) {
			data.put(i++ + "",new Object[] {Utils.GROUP_HEADER, category.getName(), category.getDescription(), category.getStatus() });

			for (Skill skill : category.getSkills()) {
				data.put(i++ + "", new Object[] {Utils.MEMBER, skill.getName(), skill.getDescription(), skill.getStatus() });
			}
		}
		
		Utils.writeToExcel(fileName, sheetName, data);
		Utils.addMessage("Export to file!");
	}
}
