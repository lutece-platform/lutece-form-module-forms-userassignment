package fr.paris.lutece.plugins.forms.modules.userassignment.web.form.column.display.factory.impl;

import fr.paris.lutece.plugins.forms.business.form.column.IFormColumn;
import fr.paris.lutece.plugins.forms.modules.userassignment.business.form.column.impl.FormColumnFormResponseAssignee;
import fr.paris.lutece.plugins.forms.modules.userassignment.web.form.column.display.impl.FormColumnDisplayFormResponseAssignee;
import fr.paris.lutece.plugins.forms.web.form.column.display.IFormColumnDisplay;
import fr.paris.lutece.plugins.forms.web.form.column.display.factory.IFormColumnDisplayFactory;

public class FormColumnDisplayFormResponseAssigneeFactory implements IFormColumnDisplayFactory
{

    @Override
    public IFormColumnDisplay buildFormColumnDisplay( IFormColumn formColumn )
    {
        FormColumnDisplayFormResponseAssignee formColumnDisplayFormResponseAssignee = null;

        if ( formColumn instanceof FormColumnFormResponseAssignee )
        {
            formColumnDisplayFormResponseAssignee = new FormColumnDisplayFormResponseAssignee( );
            formColumnDisplayFormResponseAssignee.setFormColumn( formColumn );
        }

        return formColumnDisplayFormResponseAssignee;
    }

}
