package fr.paris.lutece.plugins.forms.modules.userassignment.web.form.column.display.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import fr.paris.lutece.plugins.forms.business.form.column.FormColumnCell;
import fr.paris.lutece.plugins.forms.business.form.search.FormResponseSearchItem;
import fr.paris.lutece.plugins.forms.modules.userassignment.business.form.querypart.impl.FormColumnFormResponseAssigneeQueryPart;
import fr.paris.lutece.plugins.forms.web.form.column.display.impl.AbstractFormColumnDisplay;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.template.AppTemplateService;

public class FormColumnDisplayFormResponseAssignee extends AbstractFormColumnDisplay
{

    // Templates
    private static final String FORM_COLUMN_HEADER_TEMPLATE = "admin/plugins/forms/modules/userassignment/column/form_column_form_response_assignee_header.html";
    private static final String FORM_COLUMN_CELL_TEMPLATE = "admin/plugins/forms/modules/userassignment/column/form_column_form_response_assignee_cell.html";

    // Marks
    private static final String MARK_FORM_RESPONSE_ASSIGNEE_COLUMN_TITLE = "column_title";
    private static final String MARK_SORT_URL = "sort_url";
    private static final String MARK_FORM_RESPONSE_ASSIGNEE = "form_response_assignee";
    private static final String MARK_COLUMN_SORT_ATTRIBUTE = "column_sort_attribute";

    @Override
    public String buildFormColumnHeaderTemplate( String strSortUrl, Locale locale )
    {
        Map<String, Object> model = new LinkedHashMap<>( );
        model.put( MARK_SORT_URL, buildCompleteSortUrl( strSortUrl ) );
        model.put( MARK_FORM_RESPONSE_ASSIGNEE_COLUMN_TITLE, getFormColumnTitle( locale ) );
        model.put( MARK_COLUMN_SORT_ATTRIBUTE, FormResponseSearchItem.FIELD_ID_ASSIGNEE_USER );

        String strFormResponseDateCreationHeaderTemplate = AppTemplateService.getTemplate( FORM_COLUMN_HEADER_TEMPLATE, locale, model ).getHtml( );
        setFormColumnHeaderTemplate( strFormResponseDateCreationHeaderTemplate );

        return strFormResponseDateCreationHeaderTemplate;
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public String buildFormColumnCellTemplate( FormColumnCell formColumnCell, Locale locale )
    {
        String assigneeList = "";
        if ( formColumnCell != null )
        {
            Object objectAssigneeList = formColumnCell.getFormColumnCellValueByName( FormColumnFormResponseAssigneeQueryPart.KEY_USER_LIST );
            if ( objectAssigneeList != null )
            {
                List<AdminUser> userList = (List<AdminUser>) objectAssigneeList;
                assigneeList = userList.stream( ).map( u -> u.getFirstName( ) + " " + u.getLastName( ) )
                .collect( Collectors.joining( ", " ) );
            }
            
        }
        
        Map<String, Object> model = new LinkedHashMap<>( );
        model.put( MARK_FORM_RESPONSE_ASSIGNEE, assigneeList );

        return AppTemplateService.getTemplate( FORM_COLUMN_CELL_TEMPLATE, locale, model ).getHtml( );
    }

}
