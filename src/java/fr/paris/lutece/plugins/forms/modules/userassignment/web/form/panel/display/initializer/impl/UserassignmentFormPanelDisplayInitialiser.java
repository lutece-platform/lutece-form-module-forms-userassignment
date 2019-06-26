package fr.paris.lutece.plugins.forms.modules.userassignment.web.form.panel.display.initializer.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.forms.business.FormResponse;
import fr.paris.lutece.plugins.forms.web.form.panel.display.initializer.impl.FormPanelFormResponseIdFilterDisplayInitialiser;
import fr.paris.lutece.plugins.userassignment.business.ResourceUser;
import fr.paris.lutece.plugins.userassignment.business.ResourceUserHome;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AdminUserService;

public class UserassignmentFormPanelDisplayInitialiser extends FormPanelFormResponseIdFilterDisplayInitialiser {

	@Override
	protected List<Integer> getIdList(HttpServletRequest request) {
		List<Integer> formReponseIdList = new ArrayList<>( );
		AdminUser currentUser = AdminUserService.getAdminUser( request );
		if ( currentUser != null )
		{
			List<ResourceUser> resourceList = ResourceUserHome.findByUser(currentUser.getUserId( ), FormResponse.RESOURCE_TYPE );
			formReponseIdList.addAll( resourceList.stream( ).map( ResourceUser::getIdResource ).collect( Collectors.toList( ) ) );
		}
		return formReponseIdList;
	}
}
