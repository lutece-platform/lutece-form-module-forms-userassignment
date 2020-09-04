/*
 * Copyright (c) 2002-2020, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
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
                assigneeList = userList.stream( ).map( u -> u.getFirstName( ) + " " + u.getLastName( ) ).collect( Collectors.joining( ", " ) );
            }

        }

        Map<String, Object> model = new LinkedHashMap<>( );
        model.put( MARK_FORM_RESPONSE_ASSIGNEE, assigneeList );

        return AppTemplateService.getTemplate( FORM_COLUMN_CELL_TEMPLATE, locale, model ).getHtml( );
    }

}
