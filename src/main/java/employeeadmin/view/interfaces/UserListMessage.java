package employeeadmin.view.interfaces;

import employeeadmin.model.vo.UserVO;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/8/15
 */
public interface UserListMessage {

	void onNew();

	void onSelect( UserVO user );

	void onDelete( UserVO user );

}
