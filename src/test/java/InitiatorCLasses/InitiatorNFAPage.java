    package InitiatorCLasses;

    import Utils.WaitUtils;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import org.openqa.selenium.support.PageFactory;
    import org.openqa.selenium.support.ui.Select;
    import org.testng.Assert;

    import java.util.List;

    public class InitiatorNFAPage {
        WaitUtils waitUtils = new WaitUtils();
        WebDriver driver;

        public InitiatorNFAPage(WebDriver driver) {

            this.driver = driver;
            PageFactory.initElements(driver, this);

        }

        @FindBy(name = "P9_IMPORTANCE")
        private WebElement documentImportanceSelection;

        @FindBy(name = "P9_NFA_NUMBER")
        private WebElement NFANumbeerSelection;

        @FindBy(css = "#P9_TYPE_OF_NOTE_lov_btn")
        private WebElement NFATypeSelection;
        //Inside NFA Type Options
        @FindBy(xpath = "//li[normalize-space()='General']")
        private WebElement GeneralSelection;

        @FindBy(css = "#P9_IS_PRIVATE_NFA")
        private WebElement NFAConfidentialitySelection;

        @FindBy(css = "#P9_SUMMARY_SHEET")
        private WebElement NFASummarySheet;
        //Inside Summary Sheet Options
        @FindBy(css = "[id='summary-sheet']")
        private WebElement SummarySheetAddbutton;
        @FindBy(xpath = "(//*[.='View summary sheet'])[3]")
        private WebElement SummarySheetViewButton;
        @FindBy(css = "#P34_SUMMARY_QUESTIONS")
        private WebElement SummaryQuestionsAddbutton;
        @FindBy(xpath="(//*[@class='t-Button t-Button--icon t-Button--iconLeft t-Button--hot'])[1]")
        private WebElement SummartAnswersaddButton;
        @FindBy(css = "#P34_SUMMARY_ANSWERS")
        private WebElement SummaryAnswersAddbutton;
        @FindBy(css = ".t-Icon.t-Icon--left.fa.fa-plus-square-o")
        private WebElement SummaryQnACreateButton;
        @FindBy(xpath = "(//*[@class='t-Button t-Button--icon t-Button--iconLeft t-Button--hot'])[2]")
        private WebElement sheetCloseButton;
    // Summary Sheet Options Ended

        @FindBy(xpath = "//input[@id='P9_TOP_SHEET']")
        private WebElement NFATopSheet;
        //Inside Top Sheet Options
        @FindBy(xpath = "//input[@id='P9_TOP_SHEET_VALUE']")
        private WebElement TopSheetdatabox;
        @FindBy(xpath = "//li[normalize-space()='Confimation']")
        private WebElement ConfimationSelection;
        // Top Sheet Options Ended

        @FindBy(xpath = "//*[contains(text(),'Print Dossier')]")
        private WebElement PrintDossierButton;

        @FindBy(name = "P9_SUBJECT")
        private WebElement SubjectSelection;

        // Descrption All Feature Buttons starting Below
        @FindBy(css = "[class='ck-blurred ck ck-content ck-editor__editable ck-rounded-corners ck-editor__editable_inline']")
        private WebElement FormDescription;
        @FindBy(xpath = "(//*[@class='ck ck-icon ck-reset_all-excluded ck-icon_inherit-color ck-dropdown__arrow'])[1]")
        private WebElement DescriptionFormatTypeSelectionbutton;
        @FindBy(xpath = "(//*[@class='ck ck-button ck-off'])[1]")
        private WebElement CursiverWritingSelectionbutton;
        @FindBy(xpath = "(//*[@class='ck ck-button ck-off'])[2]")
        private WebElement UnderlineWritingSelectionbutton;
        // Descrption All Feature Buttons Ended Here

        @FindBy(css = "#UPLOAD_DOCUMENT")
        private WebElement NFAFormPageUploadDocumentButton;
        // Document Uploading options below
        @FindBy(id = "P17_DOCUMENT_TYPE")
        private WebElement DocumentTypeSelection;
        @FindBy(xpath = "(//*[.='Upload'])[4]")
        private WebElement UploadDocFileUploadButton;
        @FindBy(xpath = "//button[@id='B75097899601778816566']")
        private WebElement DocumentUploadCancelbutton;
        @FindBy(id = "P17_DOCUMENT_input")
        private WebElement DocumentFilepathSelectionbutton;
        // Document Uploading options ended here

        @FindBy(xpath = "(//*[.='Discard & New'])[2]")
        private WebElement NFAApprovalDiscardnAddbutton;
        //Starting below UnderDiscardOptions
        @FindBy(id = "P29_DEPARTMENT")
        private WebElement UnderDiscardDepartmentSelection;
        @FindBy(name = "P29_DESIGNATION")
        private WebElement UnderDiscardDesignationSelection;
        @FindBy(name = "P29_USERNAME")
        private WebElement UnderDiscardUsernameSelection;
        @FindBy(xpath = "//button[@id='add-btn']")
        private WebElement UnderDiscardAddbutton;
        @FindBy(xpath = "//span[@class='t-Icon t-Icon--left fa fa-save']")
        private WebElement UnderDiscardConfirmbutton;
        @FindBy(xpath = "//span[normalize-space()='Cancel']")
        private WebElement UnderDiscardCancelbutton;
        //Ending UnderDiscardOptions

        // Approval Matrix CheckBox
            @FindBy(xpath = " (//*[@name='top_sheet_selected'])")
        private List<WebElement> Approvalmatrixcheckboxes;
        // Approval Matrix CheckBox Ended

        @FindBy(name = "P9_REMARKS")
        private WebElement RemarksBox;

        @FindBy(xpath = "//span[normalize-space()='Submit']")
        private WebElement SubmitButton;

        @FindBy(xpath = "//span[normalize-space()='Save as Draft']")
        private WebElement SaveAsDraftButton;

        @FindBy(xpath = "//button[@id='CANCEL_BUTTON']")
        private WebElement CancelButton;

        @FindBy(xpath = "(//*[.='Submit'])[3]")
        private WebElement NFAFormPageDialogueboxSubmitButton;

        @FindBy(xpath = "(//*[.='Cancel'])[4]")
        private WebElement NFAFormPageDialogueboxCancelButton;

        // Getters Starting Below
        public WebElement getNFAFormPageSubmitButton() {
            return NFAFormPageDialogueboxSubmitButton;
        }

        public WebElement getNFAFormPageDialogueboxCancelButton() {
            return NFAFormPageDialogueboxCancelButton;
        }

        public WebElement getUnderDiscardCancelbutton() {
            return UnderDiscardCancelbutton;
        }

        public WebElement getCancelButton() {
            return CancelButton;
        }

        public WebElement getSaveAsDraftButton() {
            return SaveAsDraftButton;
        }

        public WebElement getSubmitButton() {
            return SubmitButton;
        }

        public WebElement getRemarksBox() {
            return RemarksBox;
        }

        public WebElement getTopSheet_Confirmation_asValue() {
            return ConfimationSelection;
        }
        public List<WebElement> getApprovalmatrixcheckboxes() {
            return Approvalmatrixcheckboxes;
        }

        public WebElement getUnderDiscardConfirmbutton() {
            return UnderDiscardConfirmbutton;
        }

        public WebElement getUnderDiscardAddbutton() {
            return UnderDiscardAddbutton;
        }

        public WebElement getSummartAnswersaddButton() {
            return SummartAnswersaddButton;
        }

        public WebElement getUnderDiscardUsernameSelection() {
            return UnderDiscardUsernameSelection;
        }

        public WebElement getUnderDiscardDesignationSelection() {
            return UnderDiscardDesignationSelection;
        }

        public WebElement getUnderDiscardDepartmentSelection() {
            return UnderDiscardDepartmentSelection;
        }

        public WebElement getNFAApprovalDiscardnAddbutton() {
            return NFAApprovalDiscardnAddbutton;
        }

        public WebElement getDocumentFilepathSelectionbutton() {
            return DocumentFilepathSelectionbutton;
        }

        public WebElement getDocumentUploadCancelbutton() {
            return DocumentUploadCancelbutton;
        }

        public WebElement getUploadDocFileButton() {
            return UploadDocFileUploadButton;
        }

        public WebElement getDocumentTypeSelection() {
            return DocumentTypeSelection;
        }

        public WebElement getNFAFormPageUploadDocumentButton() {
            return NFAFormPageUploadDocumentButton;
        }

        public WebElement getUnderlineWritingSelectionbutton() {
            return UnderlineWritingSelectionbutton;
        }

        public WebElement getCursiverWritingSelectionbutton() {
            return CursiverWritingSelectionbutton;
        }

        public WebElement getDescriptionFormatTypeSelectionbutton() {
            return DescriptionFormatTypeSelectionbutton;
        }

        public WebElement getFormDescription() {
            return FormDescription;
        }

        public WebElement getSubjectSelection() {
            return SubjectSelection;
        }

        public WebElement getPrintDossierButton() {
            return PrintDossierButton;
        }

        public WebElement getTopSheetdatabox() {
            return TopSheetdatabox;
        }

        public WebElement getNFATopSheet() {
            return NFATopSheet;
        }

        public WebElement getSummarySheetViewButton() {
            return SummarySheetViewButton;
        }

        public WebElement getNFASummarySheet() {
            return NFASummarySheet;
        }

        public WebElement getSummarySheetAddbutton() {
            return SummarySheetAddbutton;
        }

        public WebElement getNFAConfidentialitySelection() {
            return NFAConfidentialitySelection;
        }

        public WebElement getNFATypeSelection() {
            return NFATypeSelection;
        }

        public WebElement getNFANumbeerSelection() {
            return NFANumbeerSelection;
        }

        public WebElement getDocumentImportanceSelection() {
            return documentImportanceSelection;
        }
        public WebElement getNFATypeGeneralSelection() {
            return GeneralSelection;
        }

        public WebElement getSummaryQnACreateButton() {
            return SummaryQnACreateButton;
        }

        public WebElement getSheetCloseButton() {
            return sheetCloseButton;
        }

        public WebElement getSummaryAnswersAddbutton() {
            return SummaryAnswersAddbutton;
        }

        public WebElement getSummaryQuestionsAddbutton() {
            return SummaryQuestionsAddbutton;
        }



        // Method Implementation Starting Below

        public void NFAFormImportanceSelection(String docrole) {
            {

                Select select = new Select(documentImportanceSelection);
                select.selectByVisibleText(docrole);
                System.out.println("NFA Importance selected is: " + documentImportanceSelection.getAttribute("value"));
                Assert.assertEquals(documentImportanceSelection.getAttribute("value"), docrole);
            }


        }
    }
