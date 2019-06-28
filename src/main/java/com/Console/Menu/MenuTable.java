package com.Console.Menu;

import com.CRUD.CrudHibernate;
import com.Console.ActionDeleteAll;
import com.Console.ActionDeleteElement;
import com.Console.IAction;
import com.Console.ObjectConsoleDefault;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MenuTable extends ObjectConsoleDefault {

    private final CrudHibernate crudHibernate = new CrudHibernate();
    private final Class ENTITY;
    private final List<Object> ROWS = new ArrayList<>();

    private Object selectedRow = null;
    private String selectedRowName = null;


    public MenuTable(String name, Class entity) {
        super(name);
        this.getACTIONS().add(createActionAdd());


        this.ENTITY = entity;
        this.action();
    }

    public static StringBuffer getAllInfoRows(Class entity, List rows) {

        List<Field> list = new ArrayList<>();          //  column
        List<Column> list0 = new ArrayList<>();

        for (Field field : entity.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            field.setAccessible(true);
            if (column != null) {
                list.add(field);
                list0.add(column);
            }
        }

        int[] sizes = new int[list.size()];                         //MaxLength
        String[][] cells = new String[rows.size() + 1][];
        for (int r = 1; r <= rows.size(); r++) {                      //values
            cells[r] = fieldsToArrayString(sizes, r - 1, list, rows);
        }
        cells[0] = fieldsNameToArrayString(sizes, list, list0);           //name


        return buildInfoRows(cells, sizes, '.', '|', '+');
    }

    //-------------------------------------

    private static StringBuffer buildInfoRows(
            String[][] cells, int[] sizes, char separatorX, char separatorY, char separatorZ) {
        StringBuffer stringBuffer = null;

        for (int i = 0; i < cells.length; i++) {
            if (stringBuffer == null) {
                stringBuffer = createFlorSeparator(sizes, separatorX, separatorZ);
                stringBuffer.append('\n');
            }

            createRowString(cells[i], sizes, separatorY, ' ', stringBuffer).append('\n');
            createFlorSeparator(sizes, separatorX, separatorZ, stringBuffer).append('\n');

        }

        return stringBuffer;
    }

    private static StringBuffer createRowString(String[] row, int[] sizes, char separatorY, char emptyChar) {
        return createRowString(row, sizes, separatorY, emptyChar, null);
    }


//-------------------

    private static StringBuffer createRowString(
            String[] row,
            int[] sizes,
            char separatorY,
            char emptyChar,
            StringBuffer string) {
        StringBuffer stringBuffer = null;

        for (int i = 0; i < row.length; i++) {
            if (stringBuffer == null) {
                stringBuffer = (string == null) ? new StringBuffer(separatorY) : string.append(separatorY);
            }

            String left = "";
            String right = "";
            int pl = sizes[i] - row[i].length();
            while (pl > 0) {
                right += emptyChar;
                pl--;
                if (pl > 0) {
                    left += emptyChar;
                    pl--;
                }
            }

            stringBuffer.append(left).append(row[i]).append(right).append(separatorY);

        }

        return stringBuffer;
    }


    //--------------------------

    private static StringBuffer createFlorSeparator(int[] sizes, char separatorX, char separatorZ) {
        return createFlorSeparator(sizes, separatorX, separatorZ, null);
    }

    private static StringBuffer createFlorSeparator(int[] sizes, char separatorX, char separatorZ, StringBuffer string) {

        StringBuffer stringBuffer = null;

        for (int i : sizes) {
            if (stringBuffer == null) {
                stringBuffer = (string == null) ? new StringBuffer().append(separatorZ) : string.append(separatorZ);
            }

            for (int length = 0; length < i; length++) {
                stringBuffer.append(separatorX);
            }

            stringBuffer.append(separatorZ);

        }

        return stringBuffer;
    }

    private static String[] fieldsNameToArrayString(int[] sizes, List<Field> list, List<Column> list0) {
        String[] row = new String[list.size()];

        for (int i = 0; i < list0.size(); i++) {
            row[i] = list0.get(i).name();
            if (row[i].isEmpty()) {
                row[i] = list.get(i).getName();
            }

            if (sizes[i] < row[i].length()) {
                sizes[i] = row[i].length();
            }
        }
        return row;
    }

    private static String[] fieldsToArrayString(int[] sizes, int numberRow, List<Field> list, List rows) {
        String[] row = new String[list.size()];
        Object obj = rows.get(numberRow);
        for (int i = 0; i < list.size(); i++) {
            try {
                Object value = list.get(i).get(obj);
                row[i] = (value == null) ? "NULL" : value.toString();
                if (row[i].length() > sizes[i]) {
                    sizes[i] = row[i].length();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return row;
    }

    //-------------------------------
    private void updateActions() {
        getACTIONS().clear();

        getACTIONS().add(createActionSelect());
        getACTIONS().add(createActionAdd());

        if (getSelectedRow() != null) {
            getACTIONS().add(createActionSet());
            getACTIONS().add(createActionDelete());
        }
        getACTIONS().add(createActionDeleteAll());
    }

    public void cancelSelect() {
        setSelectedRow(null);
        setSelectedRowName(null);
        update();
    }

    public void setSelect(String name, Object object) {
        setSelectedRow(object);
        setSelectedRowName(name);
        update();
    }

    @Override
    public void setInfo(String info) {

        super.setInfo(
                MenuTable.getAllInfoRows(getENTITY(), getROWS())
                        .append((getSelectedRow() == null) ? "<Not chosen>" : "Selected : " + getSelectedRowName())
                        .append("\n")
                        .append(info)
                        .toString());
    }

    //----------------------------------------------------
    private void updateListRows() {
        getROWS().clear();
        getROWS().addAll(getCrudHibernate().getAll(getENTITY()));
    }

    @Override
    protected void update() {
        updateListRows();
        updateActions();
        super.update();
    }


    //-----------------------------------

    private IAction createActionDeleteAll() {
        return new ActionDeleteAll(getENTITY()) {
            @Override
            public void action() {
                super.action();
                cancelSelect();
            }
        };
    }


    private IAction createActionSet() {
        return new IAction() {
            @Override
            public String getName() {
                return "Set object";
            }

            @Override
            public void action() {
                new MenuUpdateElement(getSelectedRow());
            }
        };
    }


    private IAction createActionDelete() {
        return new ActionDeleteElement(getSelectedRow()) {
            @Override
            public void action() {
                super.action();
                cancelSelect();
            }
        };
    }


    private IAction createActionSelect() {
        return new IAction() {
            @Override
            public String getName() {
                return "Select object";
            }

            @Override
            public void action() {
                MenuSelectedElement menuSelectedElement = new MenuSelectedElement(
                        "Selected " + getName(),
                        getROWS());
                if (menuSelectedElement.getSelectedRow() != null) {
                    setSelect(menuSelectedElement.getSelectedRowName(), menuSelectedElement.getSelectedRow());
                }
            }
        };
    }

    private IAction createActionAdd() {
        return new IAction() {
            @Override
            public String getName() {
                return "Created new object";
            }

            @Override
            public void action() {
                Object newObject = MenuCreateElement.createNewObject(getENTITY());
                if (newObject != null) {
                    new MenuCreateElement(newObject);
                }
                cancelSelect();
            }
        };
    }


    public CrudHibernate getCrudHibernate() {
        return crudHibernate;
    }

    public Class getENTITY() {
        return ENTITY;
    }

    public List<Object> getROWS() {
        return ROWS;
    }

    public Object getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(Object selectedRow) {
        this.selectedRow = selectedRow;
    }

    public String getSelectedRowName() {
        return selectedRowName;
    }

    public void setSelectedRowName(String selectedRowName) {
        this.selectedRowName = selectedRowName;
    }
}
