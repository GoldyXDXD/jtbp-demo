package org.teamnescafe.jtbpdemo.command;

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO(""),
    STAT("/stat"),
    ACTIVE_HOMEWORK("/active_homework"),
    HOMEWORK_HISTORY("/homework_history"),
    GROUP_TIMETABLE("/dgjadgjaokjgsdlkgdflkghdhkglkdfshgkdhghksldfkfhglkhdlkghdlkhghoipewrhgopierhgoiperhopiekhneokjoerigoierjgoierhgi"),
    TIMETABLE("/timetable"),
    STUDENT_LIST("/student_list"),
    COMPLEMENT_DB("/complement_db"),
    REAL_COMPLEMENT_DB("/asdgiasdgjasglkajshgiuyrkslejhykreguihsdoiughsdouifhguidfhguifdghuioshsfighisofhbieubhyeyuriiu"),
    ADMIN_HELP("/admin_help"),
    MAILING_MESSAGE("/mailing"),
    MAILING("/d4f3jitj32463246j3426324ekgejwogiwji4u2-olasdlasdjeopiwjwpio45jym4ipnhpwhkrwgkhrtnhrthujtrhtrjhitknhisrmlmhr32g34g");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
