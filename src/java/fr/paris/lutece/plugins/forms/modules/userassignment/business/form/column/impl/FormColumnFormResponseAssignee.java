package fr.paris.lutece.plugins.forms.modules.userassignment.business.form.column.impl;

import fr.paris.lutece.plugins.forms.business.MultiviewConfig;
import fr.paris.lutece.plugins.forms.business.form.column.impl.AbstractFormColumn;

public class FormColumnFormResponseAssignee extends AbstractFormColumn
{
    /**
     * Constructor
     * 
     * @param nFormColumnPosition
     *            The position of the FormColumn
     * @param strFormColumnTitle
     *            The title of the FormColumn
     */
    public FormColumnFormResponseAssignee( int nFormColumnPosition, String strFormColumnTitle )
    {
        super( );
        setFormColumnPosition( nFormColumnPosition );
        setFormColumnTitle( strFormColumnTitle );
    }
    
    @Override
    public boolean isDisplayed( )
    {
        return MultiviewConfig.getInstance( ).isDisplayFormsAssigneeColumn( );
    }
}
