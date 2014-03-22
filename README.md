reducer
=======

Java reducer or 2D point lists.
The module contains the following reducers:
- SimpleReducer. A simple reduction mechanism, that loops all the points and removes the smallest triangle
  formed by three neighbouring points until the given list is of the desired size.
- VisvalingamWhyattReducer. A reducer wrapper for the VisvalingamWhyattContainer, which itself can
  performs the whole Visvalingam-Whyatt-reduction logic and retains the sorted list of areas, so that
  a new point list of another size can be retrieved rapidly.
