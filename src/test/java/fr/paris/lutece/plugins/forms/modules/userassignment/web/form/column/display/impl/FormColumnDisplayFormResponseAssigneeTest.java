/*
 * Copyright (c) 2002-2026, City of Paris
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Test;

import fr.paris.lutece.plugins.forms.business.form.column.FormColumnCell;
import fr.paris.lutece.plugins.forms.modules.userassignment.business.form.querypart.impl.FormColumnFormResponseAssigneeQueryPart;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.test.LuteceTestCase;

/**
 * Renders the assignee cell of the forms multiview with the HTML auto-escaping of the core (LUT-33153): the names of the
 * assigned users are written as text.
 */
public class FormColumnDisplayFormResponseAssigneeTest extends LuteceTestCase
{
    /**
     * The first and last names of the assigned users are escaped once and joined in the cell.
     */
    @Test
    public void testAssigneeNamesWrittenAsText( )
    {
        AdminUser user = new AdminUser( );
        user.setFirstName( "<b>Ann</b>" );
        user.setLastName( "O'Brien & Co" );
        AdminUser other = new AdminUser( );
        other.setFirstName( "Bob" );
        other.setLastName( "Smith" );

        Map<String, Object> mapValues = new HashMap<>( );
        mapValues.put( FormColumnFormResponseAssigneeQueryPart.KEY_USER_LIST, List.of( user, other ) );
        FormColumnCell cell = new FormColumnCell( );
        cell.setFormColumnCellValues( mapValues );

        String strHtml = new FormColumnDisplayFormResponseAssignee( ).buildFormColumnCellTemplate( cell, Locale.ENGLISH );

        assertTrue( strHtml.contains( "&lt;b&gt;Ann&lt;/b&gt; O&#39;Brien &amp; Co, Bob Smith" ), strHtml );
        assertFalse( strHtml.contains( "<b>" ), strHtml );
        assertTrue( strHtml.trim( ).startsWith( "<td" ), strHtml );
    }

    /**
     * A response assigned to nobody gives an empty cell, so the columns of the row stay aligned.
     */
    @Test
    public void testNoAssignee( )
    {
        String strHtml = new FormColumnDisplayFormResponseAssignee( ).buildFormColumnCellTemplate( new FormColumnCell( ), Locale.ENGLISH );

        assertTrue( strHtml.trim( ).startsWith( "<td" ), strHtml );
        assertTrue( strHtml.trim( ).endsWith( "</td>" ), strHtml );
    }
}
