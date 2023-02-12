package Web.scrapping;


import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<String> eventList;

    public Calendar(String path) throws Exception {
        File file = new File(path);
        FileInputStream fin = new FileInputStream(file);

        CalendarBuilder builder = new CalendarBuilder();
        net.fortuna.ical4j.model.Calendar calendar = builder.build(fin);
        ComponentList events = calendar.getComponents(Component.VEVENT);

        eventList = new ArrayList<>();
        for (Object event : events) {
            Component component = (Component) event;
            eventList.add(component.getName());
        }
    }

    public List<String> getEventList() {
        return eventList;
    }

    public static void main(String[] args) throws Exception {
        Calendar calendar = new Calendar("/Users/edenrochman/Library/Mobile Documents/com~apple~CloudDocs/Maastricht_DKE/Year 2/Project 2-2/ULTRON/src/calendar/timetable_2023-02-09.ics");
        // /Users/edenrochman/Library/Mobile Documents/com~apple~CloudDocs/Maastricht_DKE/Year 2/Project 2-2/ULTRON/src/calendar/timetable_2023-02-09.ics
        System.out.println(calendar.getEventList());
    }
}
