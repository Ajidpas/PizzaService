package pizza.domain.order.status;

import pizza.domain.order.Order;
import pizza.domain.order.StatusState;

public enum EnumStatusState implements StatusState {
	
	NEW {

		public EnumStatusState doAction(Order order) {
			if (order.getStatus() == null) {
				order.setStatus(this);
				return this;
			} else {
				return order.getStatus();
			}
		}

	},
	
	IN_PROGRESS {

		public StatusState doAction(Order order) throws NullOrderStatusException {
			StatusState status = order.getStatus();
			if (status == EnumStatusState.NEW) {
				order.setStatus(this);
				return this;
			} else if (order.getStatus() != null) {
				return status;
			} else {
				throw new NullOrderStatusException();
			}
		}

	},
	
	DONE {
		
		public StatusState doAction(Order order) throws NullOrderStatusException {
			StatusState status = order.getStatus();
			if (status == EnumStatusState.IN_PROGRESS) {
				order.setStatus(this);
				return this;
			} else if (order.getStatus() != null) {
				return status;
			} else {
				throw new NullOrderStatusException();
			}
		}

	},
	
	CANCELED {

		public StatusState doAction(Order order) throws NullOrderStatusException {
			StatusState status = order.getStatus();
			if (status == EnumStatusState.IN_PROGRESS) {
				order.setStatus(this);
				return this;
			} else if (order.getStatus() != null) {
				return status;
			} else {
				throw new NullOrderStatusException();
			}
		}
		
	}

}
