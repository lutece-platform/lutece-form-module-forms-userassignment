package fr.paris.lutece.plugins.forms.modules.userassignment.business.form.column.querypart.factory.impl;

import fr.paris.lutece.plugins.forms.business.form.column.IFormColumn;
import fr.paris.lutece.plugins.forms.business.form.column.querypart.IFormColumnQueryPart;
import fr.paris.lutece.plugins.forms.business.form.column.querypart.factory.IFormColumnQueryPartFactory;
import fr.paris.lutece.plugins.forms.modules.userassignment.business.form.column.impl.FormColumnFormResponseAssignee;
import fr.paris.lutece.plugins.forms.modules.userassignment.business.form.querypart.impl.FormColumnFormResponseAssigneeQueryPart;

public class FormColumnFormResponseAssigneeQueryPartFactory implements IFormColumnQueryPartFactory
{

    @Override
    public IFormColumnQueryPart buildFormColumnQueryPart( IFormColumn formColumn )
    {
        IFormColumnQueryPart formColumnFormResponseAssigneeQueryPartFactory = null;

        if ( formColumn instanceof FormColumnFormResponseAssignee )
        {
            formColumnFormResponseAssigneeQueryPartFactory = new FormColumnFormResponseAssigneeQueryPart( );
        }

        return formColumnFormResponseAssigneeQueryPartFactory;
    }

}
