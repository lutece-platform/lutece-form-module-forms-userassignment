package fr.paris.lutece.plugins.forms.modules.userassignment.business.form.querypart.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import fr.paris.lutece.plugins.forms.business.FormResponse;
import fr.paris.lutece.plugins.forms.business.form.column.querypart.impl.AbstractFormColumnQueryPart;
import fr.paris.lutece.plugins.forms.business.form.search.FormResponseSearchItem;
import fr.paris.lutece.plugins.forms.service.FormsPlugin;
import fr.paris.lutece.plugins.userassignment.business.IResourceUserDAO;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public class FormColumnFormResponseAssigneeQueryPart extends AbstractFormColumnQueryPart
{
    public static final String KEY_USER_LIST = "user_list";
    
    private IResourceUserDAO resourceUserDAO = SpringContextService.getBean( IResourceUserDAO.BEAN_NAME );
    
    @Override
    protected Map<String, Object> getMapFormColumnValues( FormResponseSearchItem formResponseSearchItem )
    {
        Map<String, Object> mapFormColumnValues = new HashMap<>( );
        
        List<AdminUser> userList = resourceUserDAO.selectUserListByResource( formResponseSearchItem.getIdFormResponse( ), FormResponse.RESOURCE_TYPE, FormsPlugin.getPlugin( ) );
        
        if ( CollectionUtils.isNotEmpty( userList ) )
        {
            mapFormColumnValues.put( KEY_USER_LIST, userList );
        }
        return mapFormColumnValues;
    }

}
