package employeeadmin.view.interfaces;

import employeeadmin.model.vo.UserVO;

/**
 * @author Edward Beckett :: <Edward@EdwardBeckett.com>
 * @since :: 6/8/2015
 */
public interface UserFormMessage {

	void onAdd( UserVO user );

	void onUpdate( UserVO user );

	void onCancel();

}
