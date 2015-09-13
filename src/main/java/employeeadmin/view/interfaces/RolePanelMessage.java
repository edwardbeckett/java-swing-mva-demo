package employeeadmin.view.interfaces;

import employeeadmin.model.enums.Role;
import employeeadmin.model.vo.UserVO;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/8/2015
 */
public interface RolePanelMessage {

	void onAdd( UserVO user, Role role );

	void onRemove( UserVO user, Role role );
}
